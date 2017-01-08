package core.controllers;

import core.security.GenerateAPIKey;
import core.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/developer/")
public class DeveloperController {
    private final UserRoleService userRoleService;

    @Autowired
    public DeveloperController(UserRoleService userRoleService) {
        Assert.notNull(userRoleService, "UserRoleService must not be null!");
        this.userRoleService = userRoleService;
    }

    @RequestMapping(value = "makeMD5")
    public String makeMD5(@RequestParam(defaultValue = "") String key, ModelMap model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userRoleService.isDeveloper(userDetails.getUsername())) {
            if(!key.isEmpty()) {
                model.addAttribute("md5", GenerateAPIKey.generateKey(key,"developer"));
                model.addAttribute("key",key);
            }
            return "admin/developer/makeMD5";
        }
        return "redirect:/404";
    }
}
