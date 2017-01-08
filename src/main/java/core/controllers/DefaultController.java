package core.controllers;

import core.emails.ForgotPasswordTemplate;
import core.entities.Account;
import core.entities.ResetPassword;
import core.entities.ResetPasswordDAO;
import core.outbound.MailedIt;
import core.security.GenerateAPIKey;
import core.security.RecaptchaObjectResponse;
import core.security.ResetPasswordObject;
import core.services.AccountService;
import core.services.EnvVariablesService;
import core.services.UserService;
import core.util.CheckData;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.sql.Timestamp;

@Controller
public class DefaultController {

    private final AccountService accountService;
    private final UserService userService;
    private final ResetPasswordDAO resetPasswordDAO;
    private final MailedIt mailedIt;
    private final EnvVariablesService envVariablesService;

    public DefaultController(AccountService accountService,
                             UserService userService,
                             ResetPasswordDAO resetPasswordDAO,
                             MailedIt mailedIt,
                             EnvVariablesService envVariablesService) {
        Assert.notNull(accountService, "AccountService must not be null!");
        Assert.notNull(userService, "UserService must not be null!");
        Assert.notNull(resetPasswordDAO, "ResetPasswordDAO must not be null!");
        Assert.notNull(mailedIt, "MailedIt must not be null!");
        Assert.notNull(envVariablesService, "EnvVariablesService must not be null!");
        this.accountService = accountService;
        this.userService = userService;
        this.resetPasswordDAO = resetPasswordDAO;
        this.mailedIt = mailedIt;
        this.envVariablesService = envVariablesService;
    }

    @RequestMapping(value = "/")
    public View index(HttpServletRequest request) {
        long accountId = accountService.getAccountIdFromSession(request);
        if(accountService.getAccountType(accountId) == Account.Type.CLIENT) {
            return new RedirectView("/client/dashboard");
        } else if(accountService.getAccountType(accountId) == Account.Type.PARTNER) {
            return new RedirectView("/partner/dashboard");
        } else if(accountService.getAccountType(accountId) == Account.Type.SYSTEM_ADMIN) {
            return new RedirectView("/admin/");
        }
        return new RedirectView("/404.html");
    }

    @RequestMapping(value="login")
    public String login(ModelMap model, HttpServletRequest request) {
        String request_success_msg = (String)request.getSession().getAttribute("request_success_msg");
        System.out.println(" ---------- request_success_msg: "+request_success_msg);
        if(request_success_msg != null && !request_success_msg.trim().isEmpty()) {
            model.addAttribute("successMsg",request_success_msg);
        }
        request.getSession().removeAttribute("request_success_msg");
        return "login";
    }

    @RequestMapping(value="/logout")
    public View logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        request.getSession().setAttribute("request_success_msg","You have been logged out.");
        return new RedirectView("/");
    }

    @RequestMapping(value = "forgot")
    public String forgot(String actionSuccess, String noCaptcha, ModelMap model) {
        if(actionSuccess != null && actionSuccess.trim().length() > 0) {
            model.addAttribute("actionSuccess",actionSuccess);
        } else if(noCaptcha != null && noCaptcha.trim().length() > 0) {
            model.addAttribute("noCaptcha",noCaptcha);
        }
        model.addAttribute("site_key",envVariablesService.getRecaptchaSiteKey());
        return "/forgot";
    }

    @RequestMapping(value = "resetEmail",method = RequestMethod.POST)
    public View resetEmail(String email, HttpServletRequest request) {
        if(email != null && email.trim().length() > 4 && CheckData.isValidEmailAddress(email) && userService.emailAddressExists(email)) {
            String recaptchaURL = "https://www.google.com/recaptcha/api/siteverify?secret="+envVariablesService.getRecaptchaSecret()+"&response="+request.getParameter("g-recaptcha-response");
            RestTemplate restTemplate = new RestTemplate();
            RecaptchaObjectResponse recaptchaObjectResponse = restTemplate.postForObject(recaptchaURL,null, RecaptchaObjectResponse.class);
            if(recaptchaObjectResponse.isSuccess()) {
                long now = System.currentTimeMillis();
                long then = now+21600000;
                ResetPassword resetPassword = new ResetPassword();
                String resetKey = GenerateAPIKey.generateKey(now+"big fat key",email);
                resetPassword.setResetKey(resetKey);
                long resetToken = now+then;
                resetPassword.setResetToken(resetToken);
                resetPassword.setEmail(email);
                resetPassword.setCreated(new Timestamp(now));
                resetPassword.setExpires(new Timestamp(then));
                resetPassword.setUsed(false);
                resetPasswordDAO.save(resetPassword);
                mailedIt.generateAndSendEmail(resetPassword.getEmail(),"Reset Your Password",ForgotPasswordTemplate.createBody(userService.getFirstLastForUserName(email),resetKey,"/350$786dh4!df|yudr5#8c5468d62*4/"+resetToken),true,"Reset Password Email Sent");
                mailedIt.generateAndSendEmail(envVariableService.getAdminEmailAddress(),"PASSWORD RESET SENT for "+resetPassword.getEmail(),ForgotPasswordTemplate.createBody(userService.getFirstLastForUserName(email),resetKey,"/350$786dh4!df|yudr5#8c5468d62*4/"+resetToken), false, "Reset Password Email Send (notify admin SUCCESS)");
            } else {
                // send email to developer that this had bad captcha response
                mailedIt.generateAndSendEmail(envVariableService.getAdminEmailAddress(),"ERROR -- PASSWORD RESET ReCAPTCHA FAILED","ERROR -- PASSWORD RESET ReCAPTCHA FAILED <br>recaptchaURL: "+recaptchaURL+"<br>-----------------------------------------------------<br>recaptchaObjectResponse: "+ recaptchaObjectResponse.toString(),false, "Reset Password Email Send (notify admin ReCAPTCHA TRY FAILED)");
                return new RedirectView("/forgot?noCaptcha=noCaptcha");
            }
        } else {
            // send email to developer that email does not exist
            mailedIt.generateAndSendEmail(envVariableService.getAdminEmailAddress(),"ERROR -- PASSWORD RESET - Email Does NOT Exist","ERROR -- PASSWORD RESET - Email Does NOT Exist <br>Email Address "+email+" does not exist in our system.",false, "\"Reset Password Email Send (notify admin BAD EMAIL ADDRESS)\"");
        }
        return new RedirectView("/forgot?actionSuccess=actionSuccess");
    }

    @RequestMapping("350$786dh4!df|yudr5#8c5468d62*4/{token}")
    public String resetPassword(@PathVariable("token") Long token, String k, ModelMap model, HttpServletRequest request) {
        if(k !=null && k.trim().length() > 10 && token != null && CheckData.isNumbersOnly(token.toString())) {
            ResetPassword resetPassword = resetPasswordDAO.findByResetKeyAndResetTokenAndExpiresAfterAndUsedFalse(k,token,new Timestamp(System.currentTimeMillis()));
            if(resetPassword != null && resetPassword.getId() > 0) {
                resetPassword.setUsed(true);
                resetPasswordDAO.save(resetPassword);
                request.getSession().setAttribute("k",k);
                request.getSession().setAttribute("token",token);
                ResetPasswordObject resetPasswordObject = new ResetPasswordObject();
                resetPasswordObject.setKey(k);
                resetPasswordObject.setToken(token);
                resetPasswordObject.setEmail(resetPassword.getEmail());
                model.addAttribute("resetPasswordObject",resetPasswordObject);
                return "/newPassword";
            }
        }
        return "redirect:/404";
    }

    @RequestMapping(value = "d$621*sdfgs|2357!fgds975$j89!8ad79af/{token}", method = RequestMethod.POST)
    public String saveResetPassword(@Valid @ModelAttribute("resetPasswordObject") ResetPasswordObject resetPasswordObject, BindingResult result, @PathVariable("token") Long token, ModelMap model, HttpServletRequest request) {
        if(request.getSession().getAttribute("token") != null && token != null && token.toString().equalsIgnoreCase(request.getSession().getAttribute("token").toString())) {
            if(resetPasswordObject != null && resetPasswordObject.getKey() != null && resetPasswordObject.getKey().equalsIgnoreCase(request.getSession().getAttribute("k").toString())) {
                String passCheckResult = CheckData.validatePasswords(resetPasswordObject.getPassword1(),resetPasswordObject.getPassword2());
                if(!passCheckResult.equalsIgnoreCase("good")) {
                    result.rejectValue("password1","error.password1",passCheckResult);
                    resetPasswordObject.setToken(token);
                    resetPasswordObject.setPassword1(null);
                    resetPasswordObject.setPassword2(null);
                    model.addAttribute("resetPasswordObject",resetPasswordObject);
                    return "/newPassword";
                }
                userService.updatePassword(resetPasswordObject.getEmail(),resetPasswordObject.getPassword1());
                return "/passChangeSuccess";
            } else {
                if(resetPasswordObject == null) {
                    System.out.println("ERROR: resetPasswordObject is NULL! ---------------------------------------------------------------------");
                } else {
                    System.out.println("ERROR: Session key "+request.getSession().getAttribute("k")+" != resetPasswordObject key "+resetPasswordObject.getKey()+" ---------------------------------------------------------------------");
                }
            }
        } else {
            System.out.println("ERROR: Session Token "+request.getSession().getAttribute("token")+" != url token "+token+" ---------------------------------------------------------------------");
        }
        return "redirect:/404";
    }

    @RequestMapping(value = "client")
    public View clientRedirect() {
        return new RedirectView("/client/");
    }

    @RequestMapping(value = "register")
    public View registerRedirect() {
        return new RedirectView("/register/");
    }

    @RequestMapping(value = "admin")
    public View adminRedirect() {
        return new RedirectView("/admin/");
    }

    @RequestMapping(value = {"demo","/demo/"})
    public View demoRedirect() {
        return new RedirectView("/demo/Demo-Dashboard.html");
    }
}
