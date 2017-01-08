package core.emails;

public class ForgotPasswordTemplate {
    public static String createBody(String name, String resetKey, String reqMappingValue) {
        String title = "Hello "+name+"!";
        String content = "Please follow this link to reset your Route password.";
        String asterisk = "*If you did not request a password reset, please disregard.";
        String actionUrl = "https://routeit.cloud/"+reqMappingValue+"?k="+resetKey;
        String actionLabel = "Reset Password";
        return RouteDefaultTemplate.createBody(title,content,asterisk,actionUrl,actionLabel);
    }
}
