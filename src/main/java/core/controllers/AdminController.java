package core.controllers;

import core.entities.*;
import core.security.GenerateAPIKey;
import core.services.TransactionService;
import core.services.UserRoleService;
import core.services.UserService;
import core.util.CheckData;
import core.util.PrettyDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;

@Controller
@RequestMapping(value="/admin/")
public class AdminController {

    private final AccountDAO accountDAO;
    private final ApiKeyDAO apiKeyDAO;
    private final UserRoleService userRoleService;
    private final TransactionDAO transactionDAO;
    private final GhostPasswordDAO ghostPasswordDAO;

    @Autowired
    public AdminController(AccountDAO accountDAO, ApiKeyDAO apiKeyDAO,
                           UserRoleService userRoleService,
                           TransactionDAO transactionDAO,
                           GhostPasswordDAO ghostPasswordDAO) {
        Assert.notNull(accountDAO, "AccountDAO must not be null!");
        Assert.notNull(apiKeyDAO, "ApiKeyDAO must not be null!");
        Assert.notNull(userRoleService, "UserRoleService must not be null!");
        Assert.notNull(transactionDAO, "TransactionDAO must not be null!");
        Assert.notNull(ghostPasswordDAO, "GhostPasswordDAO must not be null!");
        this.accountDAO = accountDAO;
        this.apiKeyDAO = apiKeyDAO;
        this.userRoleService = userRoleService;
        this.transactionDAO = transactionDAO;
        this.ghostPasswordDAO = ghostPasswordDAO;
    }

    @RequestMapping(value = "/")
    public String index(ModelMap model, HttpServletRequest request) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Adds isDeveloper to the session so JSTL can see it
        if(userRoleService.isDeveloper(userDetails.getUsername())) {
            request.getSession().setAttribute("isDeveloper",true);
        }
        model.addAttribute("last60DaysTransactions",transactionDAO.findTransactionsForLast60Days());
        return "admin/index";
    }

    @RequestMapping(value = "ghost")
    public View ghost(long aId, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        String userName = "ghost@"+aId;
        GhostPassword ghostPassword = ghostPasswordDAO.findByAccountId(aId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        request.getSession().setAttribute("username",userName);
        request.getSession().setAttribute("password",ghostPassword.getPassword());
        return new RedirectView("/");
    }

    @RequestMapping(value = "viewAccounts")
    public String viewAccounts(ModelMap model, @RequestParam(defaultValue = "") String success) {
        if(success!=null&&success.trim().length()>0) {
            model.addAttribute("success", success);
        }
        Iterable<Account> accounts = accountDAO.findAll();
        model.addAttribute("accounts",accounts);
        HashMap<Long,Iterable<ApiKey>> accountApiKeys = new HashMap<>();
        for(Account account : accounts) {
            accountApiKeys.put(account.getId(),apiKeyDAO.findByAccountIdAndActive(account.getId(),true));
        }
        model.addAttribute("accountApiKeys",accountApiKeys);
        return "admin/viewAccounts";
    }

    @RequestMapping(value = "transactionsByAccount")
    public String yesterdayTransactions(@RequestParam(value = "dateRangeVar", required = false) String dateRangeVar, ModelMap model, HttpServletRequest request) {
        Timestamp startTimestamp = PrettyDate.localDateToTimestamp(LocalDate.now().minusDays(1),true);
        Timestamp endTimestamp = PrettyDate.localDateToTimestamp(LocalDate.now().minusDays(1),false);
        if(dateRangeVar != null && dateRangeVar.trim().length() >10)
        {
            System.out.println("dateRangeVar: "+dateRangeVar);
            String startDate = CheckData.getStartDate(dateRangeVar);
            System.out.println("startDate: "+startDate);
            String endDate = CheckData.getEndDate(dateRangeVar);
            System.out.println("endDate: "+endDate);
            if(startDate != null && endDate != null && startDate.length() > 5 && endDate.length() > 5) {
                request.getSession().setAttribute("transactionsByAccount-startDate",startDate);
                request.getSession().setAttribute("transactionsByAccount-endDate",endDate);
                model.addAttribute("startDateVar",startDate);
                model.addAttribute("endDateVar",endDate);
                startTimestamp = PrettyDate.localDateToTimestamp(PrettyDate.stringToLocalDate(startDate),true);
                endTimestamp = PrettyDate.localDateToTimestamp(PrettyDate.stringToLocalDate(endDate),false);
            }
        } else if(request.getSession().getAttribute("transactionsByAccount-startDate") != null) {
            String startDate = request.getSession().getAttribute("transactionsByAccount-startDate").toString();
            String endDate = request.getSession().getAttribute("transactionsByAccount-endDate").toString();
            model.addAttribute("startDateVar",startDate);
            model.addAttribute("endDateVar",endDate);
            startTimestamp = PrettyDate.localDateToTimestamp(PrettyDate.stringToLocalDate(startDate),true);
            endTimestamp = PrettyDate.localDateToTimestamp(PrettyDate.stringToLocalDate(endDate),false);
        }
        model.addAttribute("transactionsByAccount",transactionDAO.findTransactionsBetweenRangeGroupByAccount(startTimestamp,endTimestamp));
        return "admin/transactionsByAccountAndDateRange";
    }
}
