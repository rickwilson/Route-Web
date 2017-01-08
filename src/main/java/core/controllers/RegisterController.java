package core.controllers;

import core.emails.EmailVarificationTemplate;
import core.entities.*;
import core.outbound.MailedIt;
import core.security.IpAddress;
import core.services.*;
import core.termsAndConditions.TermsAndConditions_Route_v1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;

@Controller
@RequestMapping(value="/register/")
public class RegisterController {

    private final AccountService accountService;
    private final ApiKeyDAO apiKeyDAO;
    private final ApiKeyService apiKeyService;
    private final UserDAO userDAO;
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final RegisterFormDAO registerFormDAO;
    private final RegisterFormService registerFormService;
    private final MailedIt mailedIt;

    @Autowired
    public RegisterController(AccountService accountService, ApiKeyDAO apiKeyDAO, ApiKeyService apiKeyService,
                              UserDAO userDAO, UserService userService, UserRoleService userRoleService,
                              RegisterFormDAO registerFormDAO, RegisterFormService registerFormService,
                              MailedIt mailedIt) {
        Assert.notNull(accountService, "AccountService must not be null!");
        Assert.notNull(apiKeyDAO, "ApiKeyDAO must not be null!");
        Assert.notNull(apiKeyService, "ApiKeyService must not be null!");
        Assert.notNull(userDAO, "UserDAO must not be null!");
        Assert.notNull(userService, "UserService must not be null!");
        Assert.notNull(userRoleService, "UserRoleService must not be null!");
        Assert.notNull(registerFormDAO, "RegisterFormDAO must not be null!");
        Assert.notNull(registerFormService, "RegisterFormService must not be null!");
        Assert.notNull(mailedIt, "MailedIt must not be null!");
        this.accountService = accountService;
        this.apiKeyDAO = apiKeyDAO;
        this.apiKeyService = apiKeyService;
        this.userDAO = userDAO;
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.registerFormDAO = registerFormDAO;
        this.registerFormService = registerFormService;
        this.mailedIt = mailedIt;
    }

    @RequestMapping(value = "verify/{id}/{key}")
    public String verifyEmailAddress(@PathVariable("id") String id, @PathVariable("key") String key, HttpServletRequest request) {
        ApiKey apiKey =  apiKeyDAO.findByApiKey(key);
        if(apiKey != null && apiKey.getId() > 0) {
            User user = userDAO.findOne(Long.parseLong(id));
            if(user != null && user.getAccountId() == apiKey.getAccountId()) {
                user.setEmailVerified(true);
                userDAO.save(user);
                request.getSession().setAttribute("request_success_msg","Success, email address verified. Log in and check it out.");
                return "redirect:/";
            }
        }
        return "redirect:/error";
    }

    @RequestMapping(value = "/")
    public String index(ModelMap model) {
        return getRegisterClient(model, false);
    }

    @RequestMapping(value = "lead")
    public String clientLead(ModelMap model) {
        return getRegisterClient(model, false);
    }

    @RequestMapping(value = "client")
    public String registerClient(ModelMap model) {
        return getRegisterClient(model, false);
    }

    @RequestMapping(value = "konnektive")
    public String registerKonnektive(ModelMap model) {
        return getRegisterClient(model, true);
    }

    private String getRegisterClient(ModelMap model, boolean preSelectedKonnektive) {
        RegisterForm registerForm = new RegisterForm();
        registerForm.setTerms(true);
        if(preSelectedKonnektive) {
            registerForm.setCrm("Konnektive");
//            model.addAttribute("specialAlert", "on");
        }
        model.addAttribute("registerForm", registerForm);
        model.addAttribute("transactionMap", registerFormService.transactionOptions());
        model.addAttribute("crmMap", registerFormService.crmOptions());
        model.addAttribute("crmOtherMap", registerFormService.crmOtherOptions());
        model.addAttribute("termsAndConditions", TermsAndConditions_Route_v1.webTermsAndConditions);
        return "client/routeRegister";
    }

    /**
     * Saves a newly populated registerForm object and redirects to thank-you page
     */
    @RequestMapping(value = "client/save", method = RequestMethod.POST)
    public String saveNewRegistration(@Valid @ModelAttribute("registerForm") RegisterForm registerForm, BindingResult result, Model model, HttpServletRequest request) {
        // check email address
        if(userService.emailAddressExists(registerForm.getEmail())) {
            result.rejectValue("email","error.email","Email address already in use. Either register with another email address or log in.");
        }
        // check check company name
        if(accountService.companyNameExists(registerForm.getCompanyName())) {
            result.rejectValue("companyName","error.companyName","Company Name already in use. Please pick a different Company Name.");
        }
        if(result.hasErrors()) {
            model.addAttribute("transactionMap", registerFormService.transactionOptions());
            model.addAttribute("crmMap", registerFormService.crmOptions());
            model.addAttribute("crmOtherMap", registerFormService.crmOtherOptions());
            model.addAttribute("termsAndConditions", TermsAndConditions_Route_v1.webTermsAndConditions);
            return "client/routeRegister";
        } else {
            // save new account, user, and APIKey
            long accountId = accountService.createAccount(registerForm.getCompanyName(),Account.Type.CLIENT);
            long userId = userService.createUser(accountId, registerForm.getEmail(), registerForm.getPassword(), registerForm.getFirst(), registerForm.getLast());
            userRoleService.createRole(userId,"ACCOUNT_ADMIN");
            ApiKey apiKeyObject = apiKeyService.createInitialApiKey(accountId,registerForm.getCompanyName(),userId,registerForm.getFirst()+" "+registerForm.getLast(),false);
            long ghostId = userService.createGhost(accountId);
            userRoleService.createRole(ghostId,"ACCOUNT_ADMIN");
            userRoleService.createRole(ghostId,"SECRET_VIEWER");
            // save new registration
            registerForm.setCreated(new Timestamp(System.currentTimeMillis()));
            registerForm.setIpAddress(IpAddress.getIpAddress(request));
            registerForm.setAccountId(accountId);
            registerForm.setUserId(userId);
            registerForm.setApiKey(apiKeyObject.getApiKey());
            registerFormDAO.save(registerForm);
            // send confirmation email
            mailedIt.generateAndSendEmail(registerForm.getEmail(),"Email Verification", EmailVarificationTemplate.createBody(registerForm.getFirst()+" "+registerForm.getLast(),registerForm.getUserId(),registerForm.getApiKey()),true, "New Registration, Verify Email Address");
            request.getSession().setAttribute("request_success_msg","Success, your registration is complete! Your new account is setup and ready to go. Log in and check it out.");
            return "redirect:/";
        }
    }
}
