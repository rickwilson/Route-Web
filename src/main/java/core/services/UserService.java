package core.services;

import core.entities.GhostPassword;
import core.entities.GhostPasswordDAO;
import core.entities.User;
import core.entities.UserDAO;
import core.security.GenerateAPIKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@Service
public class UserService {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final GhostPasswordDAO ghostPasswordDAO;

    @Autowired
    public UserService(UserDAO userDAO, PasswordEncoder passwordEncoder, GhostPasswordDAO ghostPasswordDAO) {
        Assert.notNull(userDAO, "UserDAO must not be null!");
        Assert.notNull(passwordEncoder, "PasswordEncoder must not be null!");
        Assert.notNull(ghostPasswordDAO, "GhostPasswordDAO must not be null!");
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
        this.ghostPasswordDAO = ghostPasswordDAO;
    }

    public long createUser(long accountId, String email, String password, String firstName, String lastName) {
        User user = new User();
        user.setAccountId(accountId);
        user.setUserName(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setEnabled(1);
        user.setEmailVerified(false);
        user.setFirst(firstName);
        user.setLast(lastName);
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        userDAO.save(user);
        return user.getUserId();
    }

    public long createGhost(long accountId) {
        String ghostName = "ghost@"+accountId;
        String ghostPwd = GenerateAPIKey.generateGenericKey();

        User user = new User();
        user.setAccountId(accountId);
        user.setUserName(ghostName);
        user.setPassword(passwordEncoder.encode(ghostPwd));
        user.setEmail(ghostName);
        user.setEnabled(1);
        user.setEmailVerified(true);
        user.setFirst("Ghost");
        user.setLast("Spookerson");
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        userDAO.save(user);
        GhostPassword ghostPassword = new GhostPassword();
        ghostPassword.setAccountId(accountId);
        ghostPassword.setPassword(ghostPwd);
        ghostPassword.setUpdated(new Timestamp(System.currentTimeMillis()));
        ghostPasswordDAO.save(ghostPassword);
        return user.getUserId();
    }

    public boolean emailAddressExists(String email) {
        User user = userDAO.findByEmail(email);
        if(user == null || user.getUserId() < 1) {
            return false;
        }
        return true;
    }

    public void updatePassword(String userName, String password) {
        User user = userDAO.findByUserName(userName);
        if(user != null && user.getUserId() > 0) {
            user.setPassword(passwordEncoder.encode(password));
            userDAO.save(user);
        } else {
            System.out.println("ERROR: Can't update password for user. No user exists with username "+userName);
        }
    }

    public boolean isMyUserId(long uId, HttpServletRequest request) {
        if(uId == getUserIdFromSession(request)) {
            return true;
        }
        return false;
    }

    @Cacheable("getAccountIdForUserName")
    public long getAccountIdForUserName(String userName) {
        return userDAO.findByUserName(userName).getAccountId();
    }

    @Cacheable("getFirstLastForUserName")
    public String getFirstLastForUserName(String userName) {
        User user = userDAO.findByUserName(userName);
        String firstLast = user.getFirst()+" "+user.getLast();
        return firstLast;
    }

    public void loadUserSessionStuff(HttpServletRequest request) {
        getUserIdFromSession(request);
        getUserNameFromSession(request);
        getAccountIdFromSession(request);
        getFirstLastFromSession(request);
    }

    public long getUserIdFromSession(HttpServletRequest request) {
        Long userId = (Long)request.getSession().getAttribute("myUserId");
        if(userId == null || userId < 1) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userId = userDAO.findByUserName(userDetails.getUsername()).getUserId();
            request.getSession().setAttribute("myUserId",userId);
        }
        return userId;
    }

    public String getUserNameFromSession(HttpServletRequest request) {
        String userName = (String)request.getSession().getAttribute("myUserName");
        if(userName == null || userName.trim().isEmpty()) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userName = userDetails.getUsername();
            request.getSession().setAttribute("myUserName",userName);
        }
        return userName;
    }

    public long getAccountIdFromSession(HttpServletRequest request) {
        Long accountId = (Long)request.getSession().getAttribute("myAccountId");
        if(accountId == null || accountId < 1) {
            accountId = userDAO.findByUserName(getUserNameFromSession(request)).getAccountId();
            request.getSession().setAttribute("myAccountId",accountId);
        }
        return accountId;
    }

    public String getFirstLastFromSession(HttpServletRequest request) {
        String firstLastString = (String)request.getSession().getAttribute("firstLast");
        if(firstLastString == null || firstLastString.trim().length() < 1) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            firstLastString = getFirstLastForUserName(userDetails.getUsername());
            request.getSession().setAttribute("firstLast",firstLastString);
            request.getSession().setAttribute("myFirstLast",firstLastString);
        }
        return firstLastString;
    }

    @CacheEvict(value = { "isDeveloper","getAccountIdForUserName","getFirstLastForUserName" }, allEntries = true)
    public void evictUserCaches() {
    }

    @Scheduled(cron = "0 1 1 * * ?")
    public void runEvict() {
        evictUserCaches();
    }
}
