package core.services;

import core.entities.Account;
import core.entities.AccountDAO;
import core.util.PrettyDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@Service
public class AccountService {
    private final AccountDAO accountDAO;
    private final UserService userService;

    @Autowired
    public AccountService(AccountDAO accountDAO, UserService userService) {
        Assert.notNull(accountDAO, "AccountDAO must not be null!");
        Assert.notNull(userService, "UserService must not be null!");
        this.accountDAO = accountDAO;
        this.userService = userService;
    }

    public long createAccount(String accountName, Account.Type accountType) {
        Account account = new Account();
        account.setName(accountName);
        account.setType(accountType);
        account.setActive(true);
        account.setCreated(new Timestamp(System.currentTimeMillis()));
        accountDAO.save(account);
        return account.getId();
    }

    public boolean companyNameExists(String name) {
        Account account = accountDAO.findByName(name);
        if(account == null || account.getId() < 1) {
            return false;
        }
        return true;
    }

    public long getAccountIdFromSession(HttpServletRequest request) {
        String accountIdString = (String)request.getSession().getAttribute("accountId");
        if(accountIdString == null || accountIdString.trim().length() < 1) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            long accoutId = userService.getAccountIdForUserName(userDetails.getUsername());
            accountIdString = accoutId+"";
            request.getSession().setAttribute("accountId",accoutId+"");
        }
        return Long.parseLong(accountIdString);
    }

    @Cacheable("getAccountName")
    public String getAccountName(long accountId) {
        return getAccount(accountId).getName();
    }

    @Cacheable("getAccountType")
    public Account.Type getAccountType(long accountId) {
        return getAccount(accountId).getType();
    }

    @Cacheable("getAccountTypeName")
    public String getAccountTypeName(long accountId) {
        return getAccount(accountId).getType().name();
    }

    @Cacheable("getAccountCreatedPretty")
    public String getAccountCreatedPretty(long accountId) {
        return PrettyDate.makeItPrettyDateOnly(getAccount(accountId).getCreated());
    }

    @Cacheable("getAccountCreated")
    public Timestamp getAccountCreated(long accountId) {
        return getAccount(accountId).getCreated();
    }

    public Account getAccount(long accountId) {
        return accountDAO.findOne(accountId);
    }
}
