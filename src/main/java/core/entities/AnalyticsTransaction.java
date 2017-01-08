package core.entities;

public class AnalyticsTransaction {

    private String accountName;
    private long transactionId;
    private String status;
    private String when;

    public AnalyticsTransaction() {
    }

    public AnalyticsTransaction(String accountName, long transactionId, String status, String when) {
        this.accountName = accountName;
        this.transactionId = transactionId;
        this.status = status;
        this.when = when;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }
}
