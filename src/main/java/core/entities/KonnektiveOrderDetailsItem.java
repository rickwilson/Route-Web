package core.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class KonnektiveOrderDetailsItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(nullable = false)
    private String crmPushApiKey;

    @NotNull
    @Column(nullable = false)
    private long accountId;

    @NotNull
    @Column(nullable = false)
    private String apiKey;

    @NotNull
    @Column(nullable = false)
    private long konnektiveOrderDetailsId;

    @NotNull
    @Column(nullable = false)
    private String orderId;

    private String productId;    // 1
    private String name;    // LaFolie Hair Growth Serum
    private String qty;    // 1
    private String shipping;    // 4.97
    private String price;    // 0.00
    private String initialSalesTax;    // 0.00
    private String customSalesTax;    // 0.00
    private String refundRemaining;    // 4.97
    private String purchaseStatus;    // TRIAL
    private String billingCycleType;    // RECURRING
    private String finalBillingCycle;    //
    private String isPreauthVoid;    // 0
    private String nextBillDate;    // 2016-10-04
    private String trialEnabled;    // 1
    private String trialType;    // STANDARD
    private String regularPrice;    // 89.97
    private String productQty;    // 1
    private String cycle1_billDelay;    // 18
    private String cycle2_price;    // 89.97
    private String cycle2_shipPrice;    // 0.00
    private String cycle2_isShippable;    // 0
    private String cycle2_billDelay;    // 30
    private String cycle3_price;    // 89.97
    private String cycle3_shipPrice;    // 7.97
    private String cycle3_isShippable;    // 1
    private String cycle3_billDelay;    // 30
    private String lastCustomCycle;    // 3
    private String taxRate;    //
    private String purchaseCycle;    // 1
    private String txnType;    // SALE
    private String cancellationScheduled;    // 0
    private String cancelAfterDate;    //
    private String billingCycleNumber;    // 1
    private String staggerIntervalCycles;    // 2
    private String staggerFulfillments;    // 1

    public KonnektiveOrderDetailsItem() {

    }

    public KonnektiveOrderDetailsItem(String crmPushApiKey, long accountId, String apiKey, long konnektiveOrderDetailsId, String orderId, String productId, String name, String qty, String shipping, String price, String initialSalesTax, String customSalesTax, String refundRemaining, String purchaseStatus, String billingCycleType, String finalBillingCycle, String isPreauthVoid, String nextBillDate, String trialEnabled, String trialType, String regularPrice, String productQty, String cycle1_billDelay, String cycle2_price, String cycle2_shipPrice, String cycle2_isShippable, String cycle2_billDelay, String cycle3_price, String cycle3_shipPrice, String cycle3_isShippable, String cycle3_billDelay, String lastCustomCycle, String taxRate, String purchaseCycle, String txnType, String cancellationScheduled, String cancelAfterDate, String billingCycleNumber, String staggerIntervalCycles, String staggerFulfillments) {
        this.crmPushApiKey = crmPushApiKey;
        this.accountId = accountId;
        this.apiKey = apiKey;
        this.konnektiveOrderDetailsId = konnektiveOrderDetailsId;
        this.orderId = orderId;
        this.productId = productId;
        this.name = name;
        this.qty = qty;
        this.shipping = shipping;
        this.price = price;
        this.initialSalesTax = initialSalesTax;
        this.customSalesTax = customSalesTax;
        this.refundRemaining = refundRemaining;
        this.purchaseStatus = purchaseStatus;
        this.billingCycleType = billingCycleType;
        this.finalBillingCycle = finalBillingCycle;
        this.isPreauthVoid = isPreauthVoid;
        this.nextBillDate = nextBillDate;
        this.trialEnabled = trialEnabled;
        this.trialType = trialType;
        this.regularPrice = regularPrice;
        this.productQty = productQty;
        this.cycle1_billDelay = cycle1_billDelay;
        this.cycle2_price = cycle2_price;
        this.cycle2_shipPrice = cycle2_shipPrice;
        this.cycle2_isShippable = cycle2_isShippable;
        this.cycle2_billDelay = cycle2_billDelay;
        this.cycle3_price = cycle3_price;
        this.cycle3_shipPrice = cycle3_shipPrice;
        this.cycle3_isShippable = cycle3_isShippable;
        this.cycle3_billDelay = cycle3_billDelay;
        this.lastCustomCycle = lastCustomCycle;
        this.taxRate = taxRate;
        this.purchaseCycle = purchaseCycle;
        this.txnType = txnType;
        this.cancellationScheduled = cancellationScheduled;
        this.cancelAfterDate = cancelAfterDate;
        this.billingCycleNumber = billingCycleNumber;
        this.staggerIntervalCycles = staggerIntervalCycles;
        this.staggerFulfillments = staggerFulfillments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCrmPushApiKey() {
        return crmPushApiKey;
    }

    public void setCrmPushApiKey(String crmPushApiKey) {
        this.crmPushApiKey = crmPushApiKey;
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

    public long getKonnektiveOrderDetailsId() {
        return konnektiveOrderDetailsId;
    }

    public void setKonnektiveOrderDetailsId(long konnektiveOrderDetailsId) {
        this.konnektiveOrderDetailsId = konnektiveOrderDetailsId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInitialSalesTax() {
        return initialSalesTax;
    }

    public void setInitialSalesTax(String initialSalesTax) {
        this.initialSalesTax = initialSalesTax;
    }

    public String getCustomSalesTax() {
        return customSalesTax;
    }

    public void setCustomSalesTax(String customSalesTax) {
        this.customSalesTax = customSalesTax;
    }

    public String getRefundRemaining() {
        return refundRemaining;
    }

    public void setRefundRemaining(String refundRemaining) {
        this.refundRemaining = refundRemaining;
    }

    public String getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(String purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public String getBillingCycleType() {
        return billingCycleType;
    }

    public void setBillingCycleType(String billingCycleType) {
        this.billingCycleType = billingCycleType;
    }

    public String getFinalBillingCycle() {
        return finalBillingCycle;
    }

    public void setFinalBillingCycle(String finalBillingCycle) {
        this.finalBillingCycle = finalBillingCycle;
    }

    public String getIsPreauthVoid() {
        return isPreauthVoid;
    }

    public void setIsPreauthVoid(String isPreauthVoid) {
        this.isPreauthVoid = isPreauthVoid;
    }

    public String getNextBillDate() {
        return nextBillDate;
    }

    public void setNextBillDate(String nextBillDate) {
        this.nextBillDate = nextBillDate;
    }

    public String getTrialEnabled() {
        return trialEnabled;
    }

    public void setTrialEnabled(String trialEnabled) {
        this.trialEnabled = trialEnabled;
    }

    public String getTrialType() {
        return trialType;
    }

    public void setTrialType(String trialType) {
        this.trialType = trialType;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public String getCycle1_billDelay() {
        return cycle1_billDelay;
    }

    public void setCycle1_billDelay(String cycle1_billDelay) {
        this.cycle1_billDelay = cycle1_billDelay;
    }

    public String getCycle2_price() {
        return cycle2_price;
    }

    public void setCycle2_price(String cycle2_price) {
        this.cycle2_price = cycle2_price;
    }

    public String getCycle2_shipPrice() {
        return cycle2_shipPrice;
    }

    public void setCycle2_shipPrice(String cycle2_shipPrice) {
        this.cycle2_shipPrice = cycle2_shipPrice;
    }

    public String getCycle2_isShippable() {
        return cycle2_isShippable;
    }

    public void setCycle2_isShippable(String cycle2_isShippable) {
        this.cycle2_isShippable = cycle2_isShippable;
    }

    public String getCycle2_billDelay() {
        return cycle2_billDelay;
    }

    public void setCycle2_billDelay(String cycle2_billDelay) {
        this.cycle2_billDelay = cycle2_billDelay;
    }

    public String getCycle3_price() {
        return cycle3_price;
    }

    public void setCycle3_price(String cycle3_price) {
        this.cycle3_price = cycle3_price;
    }

    public String getCycle3_shipPrice() {
        return cycle3_shipPrice;
    }

    public void setCycle3_shipPrice(String cycle3_shipPrice) {
        this.cycle3_shipPrice = cycle3_shipPrice;
    }

    public String getCycle3_isShippable() {
        return cycle3_isShippable;
    }

    public void setCycle3_isShippable(String cycle3_isShippable) {
        this.cycle3_isShippable = cycle3_isShippable;
    }

    public String getCycle3_billDelay() {
        return cycle3_billDelay;
    }

    public void setCycle3_billDelay(String cycle3_billDelay) {
        this.cycle3_billDelay = cycle3_billDelay;
    }

    public String getLastCustomCycle() {
        return lastCustomCycle;
    }

    public void setLastCustomCycle(String lastCustomCycle) {
        this.lastCustomCycle = lastCustomCycle;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getPurchaseCycle() {
        return purchaseCycle;
    }

    public void setPurchaseCycle(String purchaseCycle) {
        this.purchaseCycle = purchaseCycle;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public String getCancellationScheduled() {
        return cancellationScheduled;
    }

    public void setCancellationScheduled(String cancellationScheduled) {
        this.cancellationScheduled = cancellationScheduled;
    }

    public String getCancelAfterDate() {
        return cancelAfterDate;
    }

    public void setCancelAfterDate(String cancelAfterDate) {
        this.cancelAfterDate = cancelAfterDate;
    }

    public String getBillingCycleNumber() {
        return billingCycleNumber;
    }

    public void setBillingCycleNumber(String billingCycleNumber) {
        this.billingCycleNumber = billingCycleNumber;
    }

    public String getStaggerIntervalCycles() {
        return staggerIntervalCycles;
    }

    public void setStaggerIntervalCycles(String staggerIntervalCycles) {
        this.staggerIntervalCycles = staggerIntervalCycles;
    }

    public String getStaggerFulfillments() {
        return staggerFulfillments;
    }

    public void setStaggerFulfillments(String staggerFulfillments) {
        this.staggerFulfillments = staggerFulfillments;
    }

}
