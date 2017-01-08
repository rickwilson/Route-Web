package core.emails;

public class NewAccountEnrolledTemplate {
    public static String createBody(String emailRecipientName, String accountName, String enrolleeName, String phone, String email, String transactions, String url) {
        String title = "Hello "+emailRecipientName+"!";
        String content = "A new company just registered. <br>Company: "+accountName+
                "<br>Name: "+enrolleeName+
                "<br>Phone: "+phone+
                "<br>Email: "+email+
                "<br>URL: "+url+
                "<br>Daily Transactions: "+transactions;
        return RouteDefaultTemplate.createBody(title,content,null,null,null);
    }
}
