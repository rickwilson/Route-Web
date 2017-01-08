package core.emails;

public class EmailVarificationTemplate {
    public static String createBody(String name, long userId, String apiKey) {
        String title = "Hello "+name+"!";
        String content = "Please follow this link to verify your email address.";
        String asterisk = "*If you did not sign up for Route, please disregard.";
        String actionUrl = "https://routeit.cloud/register/verify/"+userId+"/"+apiKey;
        String actionLabel = "Activate";
        return RouteDefaultTemplate.createBody(title,content,asterisk,actionUrl,actionLabel);
    }
}
