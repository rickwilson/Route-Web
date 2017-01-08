package core.entities.subscription;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(nullable = false)
    private long accountId;

    private String name;

    @NotNull
    @Column(nullable = false)
    private String stripeBankAccountToken;

    private String stripeCustomerId;

    private boolean defaultPaymentSource;

    @NotNull
    @Column(nullable = false)
    private boolean active;

    @NotNull
    @Column(nullable = false)
    private Timestamp created;

    private long addedByUserId;

    private String addedByName;

    public Billing() {
    }

    public Billing(long accountId, String name, String stripeBankAccountToken, String stripeCustomerId, long addedByUserId, String addedByName) {
        this.accountId = accountId;
        this.name = name;
        this.stripeBankAccountToken = stripeBankAccountToken;
        this.stripeCustomerId = stripeCustomerId;
        this.active = true;
        this.created = new Timestamp(System.currentTimeMillis());
        this.addedByUserId = addedByUserId;
        this.addedByName = addedByName;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStripeBankAccountToken() {
        return stripeBankAccountToken;
    }

    public void setStripeBankAccountToken(String stripeBankAccountToken) {
        this.stripeBankAccountToken = stripeBankAccountToken;
    }

    public String getStripeCustomerId() {
        return stripeCustomerId;
    }

    public void setStripeCustomerId(String stripeCustomerId) {
        this.stripeCustomerId = stripeCustomerId;
    }

    public boolean isDefaultPaymentSource() {
        return defaultPaymentSource;
    }

    public void setDefaultPaymentSource(boolean defaultPaymentSource) {
        this.defaultPaymentSource = defaultPaymentSource;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public long getAddedByUserId() {
        return addedByUserId;
    }

    public void setAddedByUserId(long addedByUserId) {
        this.addedByUserId = addedByUserId;
    }

    public String getAddedByName() {
        return addedByName;
    }

    public void setAddedByName(String addedByName) {
        this.addedByName = addedByName;
    }
}
