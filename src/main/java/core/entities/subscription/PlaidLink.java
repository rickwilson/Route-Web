package core.entities.subscription;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
public class PlaidLink {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(nullable = false)
    private long accountId;

    private long billingId;

    @NotNull
    @Column(nullable = false)
    private String plaidClientId;

    @NotNull
    @Column(nullable = false)
    private String plaidPublicToken;

    @NotNull
    @Column(nullable = false)
    private String plaidLinkAccountId;

    private String plaidAccessToken;

    private String stripeBankAccountToken;

    @NotNull
    @Column(nullable = false)
    private String apiEndpoint;

    @Lob
    @Column(length=100000)
    private byte[] plaidResponse;

    @NotNull
    @Column(nullable = false)
    private Timestamp created;

    public PlaidLink() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getBillingId() {
        return billingId;
    }

    public void setBillingId(long billingId) {
        this.billingId = billingId;
    }

    public String getPlaidClientId() {
        return plaidClientId;
    }

    public void setPlaidClientId(String plaidClientId) {
        this.plaidClientId = plaidClientId;
    }

    public String getPlaidPublicToken() {
        return plaidPublicToken;
    }

    public void setPlaidPublicToken(String plaidPublicToken) {
        this.plaidPublicToken = plaidPublicToken;
    }

    public String getPlaidLinkAccountId() {
        return plaidLinkAccountId;
    }

    public void setPlaidLinkAccountId(String plaidLinkAccountId) {
        this.plaidLinkAccountId = plaidLinkAccountId;
    }

    public String getPlaidAccessToken() {
        return plaidAccessToken;
    }

    public void setPlaidAccessToken(String plaidAccessToken) {
        this.plaidAccessToken = plaidAccessToken;
    }

    public String getStripeBankAccountToken() {
        return stripeBankAccountToken;
    }

    public void setStripeBankAccountToken(String stripeBankAccountToken) {
        this.stripeBankAccountToken = stripeBankAccountToken;
    }

    public String getApiEndpoint() {
        return apiEndpoint;
    }

    public void setApiEndpoint(String apiEndpoint) {
        this.apiEndpoint = apiEndpoint;
    }

    public byte[] getPlaidResponse() {
        return plaidResponse;
    }

    public void setPlaidResponse(byte[] plaidResponse) {
        this.plaidResponse = plaidResponse;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}
