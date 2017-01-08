package core.controllers;

import com.bugsnag.Bugsnag;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.Stripe;
import com.stripe.model.Customer;
import core.entities.*;
import core.entities.client.*;
import core.entities.subscription.*;
import core.security.GenerateAPIKey;
import core.services.*;
import core.util.CheckData;
import core.util.PrettyDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/client/")
public class ClientController {

    private final AccountService accountService;
    private final ApiKeyDAO apiKeyDAO;
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final UserDAO userDAO;
    private final UserRolesDAO userRolesDAO;
    private final TransactionService transactionService;
    private final ShippingTrackingService shippingTrackingService;
    private final ShippingTrackingDAO shippingTrackingDAO;
    private final ShippingTrackingDetailDAO shippingTrackingDetailDAO;
    private final TransactionDAO transactionDAO;
    private final TransactionDetailDAO transactionDetailDAO;
    private final KonnektiveOrderDetailsItemDAO konnektiveOrderDetailsItemDAO;
    private final PasswordEncoder passwordEncoder;
    private final PlaidLinkDAO plaidLinkDAO;
    private final BillingDAO billingDAO;
    private final Bugsnag bugsnag;
    private final EnvVariablesService envVariablesService;

    @Autowired
    public ClientController(AccountService accountService,
                            ApiKeyDAO apiKeyDAO,
                            UserService userService,
                            UserRoleService userRoleService,
                            UserDAO userDAO,
                            UserRolesDAO userRolesDAO,
                            TransactionService transactionService,
                            ShippingTrackingService shippingTrackingService,
                            ShippingTrackingDAO shippingTrackingDAO,
                            ShippingTrackingDetailDAO shippingTrackingDetailDAO,
                            TransactionDAO transactionDAO,
                            TransactionDetailDAO transactionDetailDAO,
                            KonnektiveOrderDetailsItemDAO konnektiveOrderDetailsItemDAO,
                            PasswordEncoder passwordEncoder,
                            PlaidLinkDAO plaidLinkDAO,
                            BillingDAO billingDAO,
                            Bugsnag bugsnag,
                            EnvVariablesService envVariablesService) {
        Assert.notNull(accountService, "AccountService must not be null!");
        Assert.notNull(apiKeyDAO, "AccountService must not be null!");
        Assert.notNull(transactionService, "TransactionService must not be null!");
        Assert.notNull(shippingTrackingService, "ShippingTrackingService must not be null!");
        Assert.notNull(shippingTrackingDAO, "ShippingTrackingDAO must not be null!");
        Assert.notNull(shippingTrackingDetailDAO, "ShippingTrackingDetailDAO must not be null!");
        Assert.notNull(userService, "UserService must not be null!");
        Assert.notNull(userRoleService, "UserRoleService must not be null!");
        Assert.notNull(userDAO, "UserDAO must not be null!");
        Assert.notNull(userRolesDAO, "UserRolesDAO must not be null!");
        Assert.notNull(transactionDAO, "TransactionDAO must not be null!");
        Assert.notNull(transactionDetailDAO, "TransactionDetailDAO must not be null!");
        Assert.notNull(konnektiveOrderDetailsItemDAO, "KonnektiveOrderDetailsItemDAO must not be null!");
        Assert.notNull(passwordEncoder, "PasswordEncoder must not be null!");
        Assert.notNull(plaidLinkDAO, "PlaidLinkDAO must not be null!");
        Assert.notNull(billingDAO, "BillingDAO must not be null!");
        Assert.notNull(bugsnag, "Bugsnag must not be null!");
        Assert.notNull(envVariablesService, "EnvVariablesService must not be null!");
        this.accountService = accountService;
        this.apiKeyDAO = apiKeyDAO;
        this.transactionService = transactionService;
        this.shippingTrackingService = shippingTrackingService;
        this.shippingTrackingDAO = shippingTrackingDAO;
        this.shippingTrackingDetailDAO = shippingTrackingDetailDAO;
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.userDAO = userDAO;
        this.userRolesDAO = userRolesDAO;
        this.transactionDAO = transactionDAO;
        this.transactionDetailDAO = transactionDetailDAO;
        this.konnektiveOrderDetailsItemDAO = konnektiveOrderDetailsItemDAO;
        this.passwordEncoder = passwordEncoder;
        this.plaidLinkDAO = plaidLinkDAO;
        this.billingDAO = billingDAO;
        this.bugsnag = bugsnag;
        this.envVariablesService = envVariablesService;
    }

    @RequestMapping(value = "/")
    public View index() {
        return new RedirectView("/client/dashboard");
    }

    @RequestMapping(value = "empty")
    public String empty() {
        return "client/test/empty";
    }

    @RequestMapping(value = "dashboard")
    public String dashboard(ModelMap model, HttpServletRequest request, String message) {
        userService.loadUserSessionStuff(request);
        long accountId = accountService.getAccountIdFromSession(request);
        ClientDashboardObject clientDashboardObject = (ClientDashboardObject)request.getSession().getAttribute("clientDashboardObject");
        if(clientDashboardObject == null || clientDashboardObject.getAccountId() < 1) {
            clientDashboardObject = new ClientDashboardObject();
            clientDashboardObject.setAccountId(accountId);
            clientDashboardObject.setFirstLast(userService.getFirstLastFromSession(request));
            transactionService.populateClientDashboard(clientDashboardObject);
            shippingTrackingService.populateClientDashboard(clientDashboardObject);
            request.getSession().setAttribute("clientDashboardObject", clientDashboardObject);
        }
        System.out.println("clientDashboardObject: "+clientDashboardObject);
        model.addAttribute("clientDashboardObject", clientDashboardObject);
        model.addAttribute("accountName",accountService.getAccountName(accountId));
        model.addAttribute("accountType",accountService.getAccountTypeName(accountId));
        model.addAttribute("accountCreated",accountService.getAccountCreatedPretty(accountId));
        if(message != null && !message.isEmpty()) {
            model.addAttribute("message", message);
        }
        return "client/dashboard";
    }

    @RequestMapping(value = "transactionDetail")
    public String transactionDetail(@RequestParam(defaultValue = "0") long tId,
                                    ModelMap model, HttpServletRequest request) {
        userService.loadUserSessionStuff(request);
        long accountId = accountService.getAccountIdFromSession(request);
        if(tId > 0) {
            Transaction transaction = transactionDAO.findOne(tId);
            if( transaction != null && transaction.getAccountId() == accountId) {
                ShippingTracking shippingTracking = shippingTrackingDAO.findByTransactionId(transaction.getId());
                ClientTransactionDetailObject clientTransactionDetailObject = new ClientTransactionDetailObject(transaction, shippingTracking);
                //  Get Products
                ArrayList<ClientTransactionDetailProductObject> clientTransactionDetailProductObjects = new ArrayList<>();
                if(transaction.getOrderDetailTableId() > 0) {
                    if(transaction.getOrderDetailTable().equals(Transaction.OrderDetailTable.KonnektiveOrderDetails)) {
                        for(KonnektiveOrderDetailsItem konnektiveOrderDetailsItem : konnektiveOrderDetailsItemDAO.findByKonnektiveOrderDetailsId(transaction.getOrderDetailTableId())) {
                            clientTransactionDetailProductObjects.add(new ClientTransactionDetailProductObject(konnektiveOrderDetailsItem));
                        }
                    } else if(transaction.getOrderDetailTable().equals(Transaction.OrderDetailTable.LimeLightOrder)) {
                        // TODO: Need to collect LimeLight products
                    }
                }
                if(clientTransactionDetailProductObjects.size() > 0) {
                    clientTransactionDetailObject.setProducts(clientTransactionDetailProductObjects);
                }
                List<TransactionDetail> transactionDetails = transactionDetailDAO.findByTransactionId(transaction.getId());
                if(transactionDetails.size() > 0) {
                    clientTransactionDetailObject.setTransactionDetails((ArrayList<TransactionDetail>)transactionDetails);
                }
                if(shippingTracking != null && shippingTracking.getId() > 0) {
                    List<ShippingTrackingDetail> shippingTrackingDetails = shippingTrackingDetailDAO.findByShippingTrackingId(shippingTracking.getId());
                    if(shippingTrackingDetails.size() > 0) {
                        clientTransactionDetailObject.setShippingTrackingDetails((ArrayList<ShippingTrackingDetail>)shippingTrackingDetails);
                    }
                }
                model.addAttribute("clientTransactionDetailObject",clientTransactionDetailObject);
            } else {
                return "redirect:/500";
            }
        } else {
            return "redirect:/404";
        }
        model.addAttribute("firstLast",userService.getFirstLastFromSession(request));
        model.addAttribute("accountName",accountService.getAccountName(accountId));
        model.addAttribute("accountType",accountService.getAccountTypeName(accountId));
        model.addAttribute("accountCreated",accountService.getAccountCreatedPretty(accountId));
        return "client/transactionDetail";
    }

    @RequestMapping(value = "apiKeys")
    public String apiKeys(ModelMap model, HttpServletRequest request) {
        userService.loadUserSessionStuff(request);
        long accountId = accountService.getAccountIdFromSession(request);
        ArrayList<ClientApiKeyObject> clientApiKeyObjects = new ArrayList<>();
        for(ApiKey apiKeyObject : apiKeyDAO.findByAccountIdAndActive(accountId,true)) {
            ClientApiKeyObject clientApiKeyObject = new ClientApiKeyObject();
            clientApiKeyObject.setKey(apiKeyObject.getApiKey());
            clientApiKeyObject.setSecret(apiKeyObject.getSecret().substring(0,9)+"...");
            if(apiKeyObject.isShippingNotifications()) {
                clientApiKeyObject.setShipNotifications("Enabled");
            } else {
                clientApiKeyObject.setShipNotifications("Disabled");
            }
            clientApiKeyObject.setCreated(apiKeyObject.getCreated());
            clientApiKeyObjects.add(clientApiKeyObject);
        }
        model.addAttribute("clientApiKeyObjects",clientApiKeyObjects);
        model.addAttribute("firstLast",userService.getFirstLastFromSession(request));
        model.addAttribute("accountName",accountService.getAccountName(accountId));
        model.addAttribute("accountType",accountService.getAccountTypeName(accountId));
        model.addAttribute("accountCreated",accountService.getAccountCreatedPretty(accountId));
        return "client/apiKeys";
    }

    @RequestMapping(value = "users")
    public String users(@RequestParam(defaultValue = "") String message, ModelMap model, HttpServletRequest request) {
        userService.loadUserSessionStuff(request);
        long accountId = accountService.getAccountIdFromSession(request);
        ArrayList<ClientUserItemObject> clientUserItemObjects = new ArrayList<>();
        for(User user : userDAO.findByAccountId(accountId)) {
            if(!userRoleService.isSecretViewer(user.getUserName())) {
                ClientUserItemObject clientUserItemObject = new ClientUserItemObject();
                clientUserItemObject.setuId(user.getUserId());
                clientUserItemObject.setEmail(user.getEmail());
                clientUserItemObject.setName(user.getFirst()+" "+user.getLast());
                if(user.getEnabled() == 1) {
                    clientUserItemObject.setStatus("Active");
                } else {
                    clientUserItemObject.setStatus("Deactivated");
                }
                clientUserItemObject.setCreated(user.getCreated());
                clientUserItemObject.setDeactivated(user.getDeactivated());
                String userRoles = "";
                for(String role : userRolesDAO.findRoleByUserName(user.getUserName())) {
                    userRoles += role+" ";
                }
                clientUserItemObject.setRoles(userRoles);
                clientUserItemObjects.add(clientUserItemObject);
            }
        }
        // adds isAccountAdmin boolean to session for JSTL to pickup
        userRoleService.isAccountAdmin(request);
        if(!message.isEmpty()) {
            model.addAttribute("message", message);
        }
        model.addAttribute("clientUserItemObjects", clientUserItemObjects);
        model.addAttribute("firstLast",userService.getFirstLastFromSession(request));
        model.addAttribute("accountName",accountService.getAccountName(accountId));
        model.addAttribute("accountType",accountService.getAccountTypeName(accountId));
        model.addAttribute("accountCreated",accountService.getAccountCreatedPretty(accountId));
        return "client/users";
    }

    @RequestMapping(value = "addUser")
    public String addUser(ModelMap model, HttpServletRequest request) {
        userService.loadUserSessionStuff(request);
        if(userRoleService.isAccountAdmin(request)) {
            long accountId = accountService.getAccountIdFromSession(request);
            model.addAttribute("firstLast",userService.getFirstLastFromSession(request));
            model.addAttribute("accountName",accountService.getAccountName(accountId));
            model.addAttribute("accountType",accountService.getAccountTypeName(accountId));
            model.addAttribute("accountCreated",accountService.getAccountCreatedPretty(accountId));
            model.addAttribute("clientUserObject", new ClientUserObject());
            return "client/addUser";
        }
        return "redirect:/404";
    }

    @RequestMapping(value = "saveNewUser")
    public String saveNewUser(@Valid @ModelAttribute("clientUserObject") ClientUserObject clientUserObject, BindingResult result, ModelMap model, HttpServletRequest request) {
        userService.loadUserSessionStuff(request);
        if(userRoleService.isAccountAdmin(request)) {
            long accountId = accountService.getAccountIdFromSession(request);
            if(clientUserObject.getFirst() == null || clientUserObject.getFirst().trim().length() < 4 || !CheckData.isAlphaOnly(clientUserObject.getFirst())) {
                result.rejectValue("first","error.first","First name can only be letters and must be at least three characters long.");
            }
            if(clientUserObject.getLast() == null || clientUserObject.getLast().trim().length() < 4 || !CheckData.isAlphaOnly(clientUserObject.getLast())) {
                result.rejectValue("last","error.last","Last name can only be letters and must be at least three characters long.");
            }

            if(clientUserObject.getEmail() == null || !CheckData.isValidEmailAddress(clientUserObject.getEmail())) {
                result.rejectValue("email","error.email","Email requires a valid email address.");
            } else {
                if(userService.emailAddressExists(clientUserObject.getEmail())) {
                    result.rejectValue("email","error.email","Email address already in use.");
                }
            }

            String passCheckResult = CheckData.validatePasswords(clientUserObject.getPassword(),clientUserObject.getPassword2());
            if(!passCheckResult.equalsIgnoreCase("good")) {
                result.rejectValue("password","error.password",passCheckResult);
            }
            if(result.hasErrors()) {
                model.addAttribute("firstLast",userService.getFirstLastFromSession(request));
                model.addAttribute("accountName",accountService.getAccountName(accountId));
                model.addAttribute("accountType",accountService.getAccountTypeName(accountId));
                model.addAttribute("accountCreated",accountService.getAccountCreatedPretty(accountId));
                return "client/addUser";
            } else {
                long userId = userService.createUser(accountId,clientUserObject.getEmail(),clientUserObject.getPassword(),clientUserObject.getFirst(),clientUserObject.getLast());
                String role = "ACCOUNT_USER";
                if(clientUserObject.getRole().equalsIgnoreCase("1")) {
                    role = "ACCOUNT_ADMIN";
                }
                userRoleService.createRole(userId,role);
                return "redirect:/client/users?message=New user successfully saved.";
            }
        }
        return "redirect:/500";
    }

    @RequestMapping(value = "editMyUser")
    public View editMyUser(HttpServletRequest request) {
        userService.loadUserSessionStuff(request);
        return new RedirectView("/client/editUser?uId="+userService.getUserIdFromSession(request));
    }

    @RequestMapping(value = "editUser")
    public String editUser(@RequestParam(defaultValue = "0") long uId, ModelMap model, HttpServletRequest request) {
        userService.loadUserSessionStuff(request);
        long accountId = accountService.getAccountIdFromSession(request);
        if(uId > 0) {
            User user = userDAO.findOne(uId);
            if(user != null && user.getUserId() > 0 && user.getAccountId() == accountId) {
                ClientUserObject clientUserObject = new ClientUserObject();
                if(userService.isMyUserId(uId,request)) {
                    clientUserObject.setSelf(true);
                } else if(userRoleService.isAccountAdmin(request)) {
                    clientUserObject.setSelf(false);
                    clientUserObject.setRole("0");
                    if(userRolesDAO.findRoleByUserName(user.getUserName()).contains("ACCOUNT_ADMIN")) {
                        clientUserObject.setRole("1");
                    }
                } else {
                    return "redirect:/500";
                }
                clientUserObject.setId(user.getUserId());
                clientUserObject.setFirst(user.getFirst());
                clientUserObject.setLast(user.getLast());
                clientUserObject.setEmail(user.getEmail());
                model.addAttribute("clientUserObject", clientUserObject);
            } else {
                return "redirect:/500";
            }
        } else {
            return "redirect:/404";
        }


        model.addAttribute("firstLast",userService.getFirstLastFromSession(request));
        model.addAttribute("accountName",accountService.getAccountName(accountId));
        model.addAttribute("accountType",accountService.getAccountTypeName(accountId));
        model.addAttribute("accountCreated",accountService.getAccountCreatedPretty(accountId));
        return "client/editUser";
    }

    @RequestMapping(value = "updateUser")
    public String updateUser(@Valid @ModelAttribute("clientUserObject") ClientUserObject clientUserObject, BindingResult result, ModelMap model, HttpServletRequest request) {
        userService.loadUserSessionStuff(request);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userRoleService.isAccountAdmin(request) && !(userRoleService.isSecretViewer(userDetails.getUsername()) && userService.isMyUserId(clientUserObject.getId(),request))) {
            long accountId = accountService.getAccountIdFromSession(request);
            User user = userDAO.findOne(clientUserObject.getId());
            if(user != null && user.getAccountId() == accountId) {
                if(clientUserObject.getFirst() == null || clientUserObject.getFirst().trim().length() < 4 || !CheckData.isAlphaOnly(clientUserObject.getFirst())) {
                    result.rejectValue("first","error.first","First name can only be letters and must be at least three characters long.");
                }
                if(clientUserObject.getLast() == null || clientUserObject.getLast().trim().length() < 4 || !CheckData.isAlphaOnly(clientUserObject.getLast())) {
                    result.rejectValue("last","error.last","Last name can only be letters and must be at least three characters long.");
                }

                if(clientUserObject.getEmail() == null || !CheckData.isValidEmailAddress(clientUserObject.getEmail())) {
                    result.rejectValue("email","error.email","Email requires a valid email address.");
                } else if(!user.getEmail().equalsIgnoreCase(clientUserObject.getEmail())) {
                    if(userService.emailAddressExists(clientUserObject.getEmail())) {
                        result.rejectValue("email","error.email","Email address already in use.");
                    }
                }
                boolean updatePass = false;
                if(!(clientUserObject.getPassword() == null && clientUserObject.getPassword2() == null) &&
                        !(clientUserObject.getPassword().isEmpty() && clientUserObject.getPassword2().isEmpty())) {
                    String passCheckResult = CheckData.validatePasswords(clientUserObject.getPassword(),clientUserObject.getPassword2());
                    if(!passCheckResult.equalsIgnoreCase("good")) {
                        result.rejectValue("password","error.password",passCheckResult);
                    }
                    updatePass = true;
                }
                if(result.hasErrors()) {
                    model.addAttribute("firstLast",userService.getFirstLastFromSession(request));
                    model.addAttribute("accountName",accountService.getAccountName(accountId));
                    model.addAttribute("accountType",accountService.getAccountTypeName(accountId));
                    model.addAttribute("accountCreated",accountService.getAccountCreatedPretty(accountId));
                    return "client/editUser";
                } else {
                    user.setFirst(clientUserObject.getFirst());
                    user.setLast(clientUserObject.getLast());
                    if(!clientUserObject.getEmail().equalsIgnoreCase(user.getEmail())) {
                        user.setEmail(clientUserObject.getEmail());
                        user.setUserName(clientUserObject.getEmail());
                        user.setEmailVerified(false);
                    }
                    if(updatePass) {
                        user.setPassword(passwordEncoder.encode(clientUserObject.getPassword()));
                    }
                    userDAO.save(user);
                    if(!userService.isMyUserId(clientUserObject.getId(),request)) {
                        String role = "ACCOUNT_USER";
                        if(clientUserObject.getRole().equalsIgnoreCase("1")) {
                            role = "ACCOUNT_ADMIN";
                        }
                        userRoleService.removeRoles(user.getUserId());
                        userRoleService.createRole(user.getUserId(),role);
                    } else {
                        return "redirect:/logout";
                    }
                    return "redirect:/client/users?message=User successfully updated.";
                }
            }
        } else if (userRoleService.isSecretViewer(userDetails.getUsername()) && userService.isMyUserId(clientUserObject.getId(),request)) {
            return "redirect:/client/users?message=This user account does not have rights to change itself.";
        }
        return "redirect:/500";
    }

    @RequestMapping(value = "report/revenue")
    public String revenueReport(ModelMap model, HttpServletRequest request,
                                @RequestParam(defaultValue = "0") int pageNumber,
                                @RequestParam(defaultValue = "50") int resultSize,
                                @RequestParam(defaultValue = "m") String span,
                                @RequestParam(defaultValue = "0") int sort,
                                @RequestParam(defaultValue = "0") int dir,
                                @RequestParam(defaultValue = "") String search,
                                @RequestParam(defaultValue = "1") int searchType) {
        userService.loadUserSessionStuff(request);
        long accountId = accountService.getAccountIdFromSession(request);
        PageRequest pageRequest;
        String sortCol;
        if(sort > 0) {
            switch (sort) {
                case 1:  sortCol = "orderDate";
                    break;
                case 2:  sortCol = "orderId";
                    break;
                case 3:  sortCol = "customerLast";
                    break;
                case 4:  sortCol = "customerProvence";
                    break;
                case 5:  sortCol = "customerCountry";
                    break;
                case 6:  sortCol = "orderValue";
                    break;
                case 7:  sortCol = "revShare";
                    break;
                default: sortCol = "orderId";
                    break;
            }
            if(dir == 1) {
                pageRequest = new PageRequest(pageNumber, resultSize, new Sort(Sort.Direction.DESC, sortCol));
            } else {
                pageRequest = new PageRequest(pageNumber, resultSize, new Sort(Sort.Direction.ASC, sortCol));
            }
        } else {
            pageRequest = new PageRequest(pageNumber, resultSize);
        }


        Page<Transaction> transactions;
        if(search == null || search.trim().length() > 1) {
            switch (searchType) {
                case 2:  transactions = transactionDAO.findByAccountIdAndCreatedBetweenAndCustomerFirstContainingIgnoreCase(accountId, getFinish(span), getStart(span), search, pageRequest);
                    break;
                case 3:  transactions = transactionDAO.findByAccountIdAndCreatedBetweenAndCustomerLastContainingIgnoreCase(accountId, getFinish(span), getStart(span), search, pageRequest);
                    break;
                case 4:  transactions = transactionDAO.findByAccountIdAndCreatedBetweenAndCustomerProvenceContainingIgnoreCase(accountId, getFinish(span), getStart(span), search, pageRequest);
                    break;
                case 5:  transactions = transactionDAO.findByAccountIdAndCreatedBetweenAndCustomerCountryContainingIgnoreCase(accountId, getFinish(span), getStart(span), search, pageRequest);
                    break;
                default: transactions = transactionDAO.findByAccountIdAndCreatedBetweenAndOrderIdContainingIgnoreCase(accountId, getFinish(span), getStart(span), search, pageRequest);
                    break;
            }
        } else {
            transactions = transactionDAO.findByAccountIdAndCreatedBetween(accountId, getFinish(span), getStart(span), pageRequest);
        }

        model.addAttribute("totalPages",transactions.getTotalPages());
        model.addAttribute("totalRows",transactions.getTotalElements());
        model.addAttribute("pageNumber",pageNumber);
        model.addAttribute("resultSize",resultSize);
        model.addAttribute("span",span);
        model.addAttribute("sort",sort);
        model.addAttribute("dir",dir);
        model.addAttribute("search",search);
        model.addAttribute("searchType",searchType);
        model.addAttribute("clientReportItemObjects", populateClientReportItems(transactions));
        model.addAttribute("firstLast",userService.getFirstLastFromSession(request));
        model.addAttribute("accountName",accountService.getAccountName(accountId));
        model.addAttribute("accountType",accountService.getAccountTypeName(accountId));
        model.addAttribute("accountCreated",accountService.getAccountCreatedPretty(accountId));
        return "client/reports/revenueReport";
    }

    private Timestamp getStart(String span) {
        Timestamp start = new Timestamp(System.currentTimeMillis());
        if (span.equalsIgnoreCase("16")) {
            LocalDate startLocalDate = LocalDate.of(2016, 1, 1);
            start = Timestamp.valueOf(startLocalDate.atStartOfDay());
        }
        return start;
    }

    private Timestamp getFinish(String span) {
        LocalDate finishLocalDate = LocalDate.now();
        if(span.equalsIgnoreCase("h")) {
            finishLocalDate = finishLocalDate.minusMonths(6);
        } else if (span.equalsIgnoreCase("16")) {
            finishLocalDate = LocalDate.of(2016, 12, 31);
        } else if (span.equalsIgnoreCase("y")) {
            finishLocalDate = finishLocalDate.minusYears(1);
        } else {
            finishLocalDate = finishLocalDate.minusMonths(1);
        }
        return Timestamp.valueOf(finishLocalDate.atStartOfDay());
    }

    private ArrayList<ClientReportItemObject> populateClientReportItems(Page<Transaction> transactions) {
        ArrayList<ClientReportItemObject> clientReportItemObjects = new ArrayList<>();
        for(Transaction transaction : transactions) {
            ClientReportItemObject clientReportItemObject = new ClientReportItemObject(transaction);
            clientReportItemObjects.add(clientReportItemObject);
        }
        return clientReportItemObjects;
    }

    @RequestMapping(value = "report/transaction")
    public String transactionReport(ModelMap model, HttpServletRequest request,
                                @RequestParam(defaultValue = "0") int pageNumber,
                                @RequestParam(defaultValue = "50") int resultSize,
                                @RequestParam(defaultValue = "m") String span,
                                @RequestParam(defaultValue = "0") int stat,
                                @RequestParam(defaultValue = "0") int sort,
                                @RequestParam(defaultValue = "0") int dir,
                                @RequestParam(defaultValue = "") String search,
                                @RequestParam(defaultValue = "1") int searchType) {
        userService.loadUserSessionStuff(request);
        long accountId = accountService.getAccountIdFromSession(request);
        PageRequest pageRequest;
        String sortCol;
        if(sort > 0) {
            switch (sort) {
                case 1:  sortCol = "orderDate";
                    break;
                case 2:  sortCol = "orderId";
                    break;
                case 3:  sortCol = "customerLast";
                    break;
                case 4:  sortCol = "customerPhone";
                    break;
                case 5:  sortCol = "customerEmail";
                    break;
                case 6:  sortCol = "customerCountry";
                    break;
                case 7:  sortCol = "orderValue";
                    break;
                case 8:  sortCol = "transactionState";
                    break;
                default: sortCol = "orderId";
                    break;
            }
            if(dir == 1) {
                pageRequest = new PageRequest(pageNumber, resultSize, new Sort(Sort.Direction.DESC, sortCol));
            } else {
                pageRequest = new PageRequest(pageNumber, resultSize, new Sort(Sort.Direction.ASC, sortCol));
            }
        } else {
            pageRequest = new PageRequest(pageNumber, resultSize);
        }

        Page<Transaction> transactions;
        if(search == null || search.trim().length() > 1) {
            switch (searchType) {
                case 2:  transactions = transactionDAO.findByAccountIdAndCreatedBetweenAndCustomerFirstContainingIgnoreCase(accountId, getFinish(span), getStart(span), search, pageRequest);
                    break;
                case 3:  transactions = transactionDAO.findByAccountIdAndCreatedBetweenAndCustomerLastContainingIgnoreCase(accountId, getFinish(span), getStart(span), search, pageRequest);
                    break;
                case 4:  transactions = transactionDAO.findByAccountIdAndCreatedBetweenAndCustomerPhoneContainingIgnoreCase(accountId, getFinish(span), getStart(span), search, pageRequest);
                    break;
                case 5:  transactions = transactionDAO.findByAccountIdAndCreatedBetweenAndCustomerEmailContainingIgnoreCase(accountId, getFinish(span), getStart(span), search, pageRequest);
                    break;
                case 6:  transactions = transactionDAO.findByAccountIdAndCreatedBetweenAndCustomerProvenceContainingIgnoreCase(accountId, getFinish(span), getStart(span), search, pageRequest);
                    break;
                case 7:  transactions = transactionDAO.findByAccountIdAndCreatedBetweenAndCustomerCountryContainingIgnoreCase(accountId, getFinish(span), getStart(span), search, pageRequest);
                    break;
                default: transactions = transactionDAO.findByAccountIdAndCreatedBetweenAndOrderIdContainingIgnoreCase(accountId, getFinish(span), getStart(span), search, pageRequest);
                    break;
            }
        } else {
            transactions = transactionDAO.findByAccountIdAndCreatedBetween(accountId, getFinish(span), getStart(span), pageRequest);
        }

        model.addAttribute("totalPages",transactions.getTotalPages());
        model.addAttribute("totalRows",transactions.getTotalElements());
        model.addAttribute("pageNumber",pageNumber);
        model.addAttribute("resultSize",resultSize);
        model.addAttribute("span",span);
        model.addAttribute("stat",stat);
        model.addAttribute("sort",sort);
        model.addAttribute("dir",dir);
        model.addAttribute("search",search);
        model.addAttribute("searchType",searchType);
        model.addAttribute("clientReportItemObjects", populateClientReportItems(transactions));
        model.addAttribute("firstLast",userService.getFirstLastFromSession(request));
        model.addAttribute("accountName",accountService.getAccountName(accountId));
        model.addAttribute("accountType",accountService.getAccountTypeName(accountId));
        model.addAttribute("accountCreated",accountService.getAccountCreatedPretty(accountId));
        return "client/reports/transactionReport";
    }

    @RequestMapping(value = "report/shipping")
    public String shippingReport(ModelMap model, HttpServletRequest request,
                                 @RequestParam(defaultValue = "0") int pageNumber,
                                 @RequestParam(defaultValue = "50") int resultSize,
                                 @RequestParam(defaultValue = "m") String span,
                                 @RequestParam(defaultValue = "0") int sort,
                                 @RequestParam(defaultValue = "0") int tag,
                                 @RequestParam(defaultValue = "0") int dir,
                                 @RequestParam(defaultValue = "") String search,
                                 @RequestParam(defaultValue = "1") int searchType) {
        userService.loadUserSessionStuff(request);
        long accountId = accountService.getAccountIdFromSession(request);
        PageRequest pageRequest;
        String sortCol;
        if(sort > 0) {
            switch (sort) {
                case 1:  sortCol = "orderDate";
                    break;
                case 2:  sortCol = "trackingNumber";
                    break;
                case 3:  sortCol = "courier";
                    break;
                case 4:  sortCol = "tag";
                    break;
                case 5:  sortCol = "orderId";
                    break;
                case 6:  sortCol = "customerLast";
                    break;
                case 7:  sortCol = "customerCountry";
                    break;
                case 8:  sortCol = "orderValue";
                    break;
                default: sortCol = "trackingNumber";
                    break;
            }
            if(dir == 1) {
                pageRequest = new PageRequest(pageNumber, resultSize, new Sort(Sort.Direction.DESC, sortCol));
            } else {
                pageRequest = new PageRequest(pageNumber, resultSize, new Sort(Sort.Direction.ASC, sortCol));
            }
        } else {
            pageRequest = new PageRequest(pageNumber, resultSize);
        }

        Page<Transaction> transactions;
        if(search == null || search.trim().length() > 1) {
            switch (searchType) {
                case 2:  transactions = transactionDAO.findByAccountIdAndCreatedBetweenAndCourierContainingIgnoreCase(accountId, getFinish(span), getStart(span), search, pageRequest);
                    break;
                case 3:  transactions = transactionDAO.findByAccountIdAndCreatedBetweenAndOrderIdContainingIgnoreCase(accountId, getFinish(span), getStart(span), search, pageRequest);
                    break;
                case 4:  transactions = transactionDAO.findByAccountIdAndCreatedBetweenAndCustomerFirstContainingIgnoreCase(accountId, getFinish(span), getStart(span), search, pageRequest);
                    break;
                case 5:  transactions = transactionDAO.findByAccountIdAndCreatedBetweenAndCustomerLastContainingIgnoreCase(accountId, getFinish(span), getStart(span), search, pageRequest);
                    break;
                case 6:  transactions = transactionDAO.findByAccountIdAndCreatedBetweenAndCustomerProvenceContainingIgnoreCase(accountId, getFinish(span), getStart(span), search, pageRequest);
                    break;
                case 7:  transactions = transactionDAO.findByAccountIdAndCreatedBetweenAndCustomerCountryContainingIgnoreCase(accountId, getFinish(span), getStart(span), search, pageRequest);
                    break;
                default: transactions = transactionDAO.findByAccountIdAndCreatedBetweenAndTrackingNumberContainingIgnoreCase(accountId, getFinish(span), getStart(span), search, pageRequest);
                    break;
            }
        } else {
            transactions = transactionDAO.findByAccountIdAndCreatedBetween(accountId, getFinish(span), getStart(span), pageRequest);
        }

        model.addAttribute("totalPages",transactions.getTotalPages());
        model.addAttribute("totalRows",transactions.getTotalElements());
        model.addAttribute("pageNumber",pageNumber);
        model.addAttribute("resultSize",resultSize);
        model.addAttribute("span",span);
        model.addAttribute("tag",tag);
        model.addAttribute("sort",sort);
        model.addAttribute("dir",dir);
        model.addAttribute("search",search);
        model.addAttribute("searchType",searchType);
        model.addAttribute("clientReportItemObjects", populateClientReportItems(transactions));
        model.addAttribute("firstLast",userService.getFirstLastFromSession(request));
        model.addAttribute("accountName",accountService.getAccountName(accountId));
        model.addAttribute("accountType",accountService.getAccountTypeName(accountId));
        model.addAttribute("accountCreated",accountService.getAccountCreatedPretty(accountId));
        return "client/reports/shippingReport";
    }

    @RequestMapping(value = "billing")
    public String billing(@RequestParam(defaultValue = "") String message, ModelMap model, HttpServletRequest request) {
        userService.loadUserSessionStuff(request);
        long accountId = accountService.getAccountIdFromSession(request);
        // adds isAccountAdmin boolean to session for JSTL to pickup
        userRoleService.isAccountAdmin(request);
        if(!message.isEmpty()) {
            model.addAttribute("message", message);
        }
        model.addAttribute("billingObjects", billingDAO.findByAccountIdAndStripeCustomerIdNotNullOrderByActiveAscCreatedAscNameAsc(accountId));
        model.addAttribute("firstLast",userService.getFirstLastFromSession(request));
        model.addAttribute("accountName",accountService.getAccountName(accountId));
        model.addAttribute("accountType",accountService.getAccountTypeName(accountId));
        model.addAttribute("accountCreated",accountService.getAccountCreatedPretty(accountId));
        return "client/billing";
    }

    @RequestMapping(value = "achLink")
    public String achLink(ModelMap model, HttpServletRequest request) {
        userService.loadUserSessionStuff(request);
        model.addAttribute("firstLast",userService.getFirstLastFromSession(request));
        long accountId = accountService.getAccountIdFromSession(request);
        String accountName = accountService.getAccountName(accountId);
        String accountIdForPlaid = accountId+"-"+accountName;
        model.addAttribute("account_identifier_for_plaid",accountIdForPlaid);
        model.addAttribute("public_plaid_key",envVariablesService.getPlaidPublicKey());
        model.addAttribute("plaid_link_env_stage",envVariablesService.getPlaidLinkEnvStage());
        String acht = GenerateAPIKey.generateKey(accountIdForPlaid,"achLink");
        model.addAttribute("acht",acht);
        request.getSession().setAttribute("acht",acht);
        model.addAttribute("accountName",accountName);
        model.addAttribute("accountType",accountService.getAccountTypeName(accountId));
        model.addAttribute("accountCreated",accountService.getAccountCreatedPretty(accountId));
        model.addAttribute("paymentTypeName",userService.getFirstLastFromSession(request)+" "+ PrettyDate.makeItPrettyDateOnly(new Timestamp(System.currentTimeMillis())));
        return "client/achLink";
    }

    @RequestMapping(value = "finishAch")
    public View achLink(String pt, String mai, String acht, String ptn, HttpServletRequest request) {
        userService.loadUserSessionStuff(request);
        String message = "Payment Link Successfully Setup.";
        if(acht == null ||
                acht.isEmpty() ||
                request.getSession().getAttribute("acht") == null ||
                !acht.equals(request.getSession().getAttribute("acht")) ||
                pt == null ||
                pt.isEmpty() ||
                mai == null ||
                mai.isEmpty())
        {
            return new RedirectView("/error/404");
        }

        if(ptn == null || ptn.isEmpty()) {
            ptn = userService.getFirstLastFromSession(request)+" "+ PrettyDate.makeItPrettyDateOnly(new Timestamp(System.currentTimeMillis()));
        }

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PlaidLink plaidLink = new PlaidLink();
        long accountId = accountService.getAccountIdFromSession(request);
        plaidLink.setAccountId(accountId);
        plaidLink.setPlaidClientId(envVariablesService.getPlaidClientId());
        plaidLink.setPlaidPublicToken(pt);
        plaidLink.setPlaidLinkAccountId(mai);
        plaidLink.setApiEndpoint(envVariablesService.getPlaidApiEndpoint());
        plaidLink.setCreated(new Timestamp(System.currentTimeMillis()));
        plaidLinkDAO.save(plaidLink);

        try{
            String mappedUrl = plaidLink.getApiEndpoint()+
                                    "?client_id="+plaidLink.getPlaidClientId()+
                                    "&secret="+envVariablesService.getPlaidSecret()+
                                    "&public_token="+plaidLink.getPlaidPublicToken()+
                                    "&account_id="+plaidLink.getPlaidLinkAccountId();
            URL url = new URL(mappedUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            ObjectMapper mapper = new ObjectMapper();
            ExchangeToken exchangeToken = mapper.readValue(con.getInputStream(), ExchangeToken.class);
            plaidLink.setPlaidAccessToken(exchangeToken.getAccess_token());
            plaidLink.setStripeBankAccountToken(exchangeToken.getStripe_bank_account_token());
            try{
                plaidLink.setPlaidResponse(exchangeToken.toString().getBytes(StandardCharsets.UTF_8));
            } catch (Exception addByteArrayException) {
                plaidLink.setPlaidResponse(null);
                bugsnag.notify(addByteArrayException);
            }

            Stripe.apiKey = envVariablesService.getStripeApiKey();

            // Create the Stripe Customer
            Map<String, Object> customerParams = new HashMap<>();
            customerParams.put("source", exchangeToken.getStripe_bank_account_token());
            customerParams.put("description", "AccountId: "+accountId+"  --  AccountName: "+accountService.getAccountName(accountId)+"  --  UserId: "+userService.getUserIdFromSession(request)+"  --  UserName: "+userDetails.getUsername()+"  --  FirstLast: "+userService.getFirstLastForUserName(userDetails.getUsername()));
            customerParams.put("email", userDetails.getUsername());
            Customer customer = Customer.create(customerParams);


            Billing billing = new Billing(plaidLink.getAccountId(), ptn, exchangeToken.getStripe_bank_account_token(),customer.getId(), userService.getUserIdFromSession(request), userService.getFirstLastFromSession(request));
            billingDAO.save(billing);
            plaidLink.setBillingId(billing.getId());

        } catch (Exception e) {
            try{
                plaidLink.setPlaidResponse(e.getMessage().getBytes(StandardCharsets.UTF_8));
            } catch (Exception addByteArrayException2) {
                plaidLink.setPlaidResponse(null);
                bugsnag.notify(addByteArrayException2);
            }
            e.printStackTrace();
            message = "ERROR setting up payment link.";
            bugsnag.notify(e);
        }
        plaidLinkDAO.save(plaidLink);

        return new RedirectView("/client/billing?message="+message);
    }
}
