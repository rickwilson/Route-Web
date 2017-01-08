package core.entities.subscription;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeToken {
    private String account_id;
    private String stripe_bank_account_token;
    private boolean sandbox;
    private String access_token;

    public ExchangeToken() {
    }

    public ExchangeToken(String account_id, String stripe_bank_account_token, boolean sandbox, String access_token) {
        this.account_id = account_id;
        this.stripe_bank_account_token = stripe_bank_account_token;
        this.sandbox = sandbox;
        this.access_token = access_token;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getStripe_bank_account_token() {
        return stripe_bank_account_token;
    }

    public void setStripe_bank_account_token(String stripe_bank_account_token) {
        this.stripe_bank_account_token = stripe_bank_account_token;
    }

    public boolean isSandbox() {
        return sandbox;
    }

    public void setSandbox(boolean sandbox) {
        this.sandbox = sandbox;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @Override
    public String toString() {
        return "ExchangeToken{" +
                "account_id='" + account_id + '\'' +
                ", stripe_bank_account_token='" + stripe_bank_account_token + '\'' +
                ", sandbox=" + sandbox +
                ", access_token='" + access_token + '\'' +
                '}';
    }
}
