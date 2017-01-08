package core.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class OmniTransactionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(nullable = false)
    private long accountId;

    @NotNull
    @Column(nullable = false)
    private String apiKey;

    @NotNull
    @Column(nullable = false)
    private long omniTransactionId;

    private String itemSku;

    @NotNull
    @Column(nullable = false)
    private String itemId;                                      // REQUIRED

    @NotNull
    @Column(nullable = false)
    private int itemQty;                                        // REQUIRED

    @NotNull
    @Column(nullable = false)
    private String itemName;                                    // REQUIRED

    private String itemDesc;

    private BigDecimal itemCostPerUnit = BigDecimal.ZERO;       // REQUIRED

    private BigDecimal itemValuePerUnit = BigDecimal.ZERO;      // REQUIRED

    private String createdByIpAddress;

    private LocalDateTime createdByDateTime;

    public OmniTransactionItem() {
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

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public long getOmniTransactionId() {
        return omniTransactionId;
    }

    public void setOmniTransactionId(long omniTransactionId) {
        this.omniTransactionId = omniTransactionId;
    }

    public String getItemSku() {
        return itemSku;
    }

    public void setItemSku(String itemSku) {
        this.itemSku = itemSku;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public BigDecimal getItemCostPerUnit() {
        return itemCostPerUnit;
    }

    public void setItemCostPerUnit(BigDecimal itemCostPerUnit) {
        this.itemCostPerUnit = itemCostPerUnit;
    }

    public BigDecimal getItemValuePerUnit() {
        return itemValuePerUnit;
    }

    public void setItemValuePerUnit(BigDecimal itemValuePerUnit) {
        this.itemValuePerUnit = itemValuePerUnit;
    }

    public String getCreatedByIpAddress() {
        return createdByIpAddress;
    }

    public void setCreatedByIpAddress(String createdByIpAddress) {
        this.createdByIpAddress = createdByIpAddress;
    }

    public LocalDateTime getCreatedByDateTime() {
        return createdByDateTime;
    }

    public void setCreatedByDateTime(LocalDateTime createdByDateTime) {
        this.createdByDateTime = createdByDateTime;
    }
}
