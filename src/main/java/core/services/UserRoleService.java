package core.services;

import core.entities.UserRole;
import core.entities.UserRolesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Service
public class UserRoleService {
    private final UserRolesDAO userRolesDAO;

    @Autowired
    public UserRoleService(UserRolesDAO userRolesDAO) {
        Assert.notNull(userRolesDAO, "UserRolesDAO must not be null!");
        this.userRolesDAO = userRolesDAO;
    }

    public void createRole(long userId, String role) {
        UserRole userRole = new UserRole();
        userRole.setUserid(userId);
        userRole.setRole(role);
        userRolesDAO.save(userRole);
    }

    public void removeRoles(Long userId) {
        userRolesDAO.delete(userRolesDAO.findByUserid(userId));
    }

    @Cacheable("isAccountViewer")
    public boolean isSecretViewer(String userName) {
        boolean isSecretViewerBoolean = false;
        if(userRolesDAO.findRoleByUserName(userName).contains("SECRET_VIEWER")) {
            isSecretViewerBoolean = true;
        }
        return isSecretViewerBoolean;
    }

    @Cacheable("isDeveloper")
    public boolean isDeveloper(String userName) {
        boolean isDeveloperBoolean = false;
        if(userRolesDAO.findRoleByUserName(userName).contains("SYSTEM_DEVELOPER")) {
            isDeveloperBoolean = true;
        }
        return isDeveloperBoolean;
    }


    public boolean isAccountAdmin(HttpServletRequest request) {
        Boolean isAccountAdminBoolean = (Boolean)request.getSession().getAttribute("isAccountAdmin");
        if(isAccountAdminBoolean == null) {
            isAccountAdminBoolean = false;
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(userRolesDAO.findRoleByUserName(userDetails.getUsername()).contains("ACCOUNT_ADMIN")) {
                isAccountAdminBoolean = true;
            }
            request.getSession().setAttribute("isAccountAdmin",isAccountAdminBoolean);
        }
        return isAccountAdminBoolean;
    }
}
