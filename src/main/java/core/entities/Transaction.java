package core.entities;

import core.entities.enums.ShippingNotificationType;
import core.entities.enums.TransactionState;
import core.thirdParty.aftership.enums.StatusTag;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
public class Transaction {

    public static enum CrmType { UNKNOWN, LIME_LIGHT_INTEGRATED, LIME_LIGHT_ENTERPRISE, KONNECTIVE_INTEGRATED, KONNEKTIVE_ENTERPRISE, OPEN_API };
    public static enum OrderDetailTable { UNKNOWN, KonnektiveOrderDetails, LimeLightOrder, OpenApiOrder };
    public static enum IteratorTable { NONE, UNKNOWN, LimeLightInitializer };
//    public static enum ShippingNotificationType { NONE, EMAIL, SMS, EMAIL_AND_SMS};

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(nullable = false)
    private long accountId;

    @NotNull
    @Column(nullable = false)
    private String apiKey;

    private String crmPushApiKey;

    private String insPrice;
    private String insTermPrice;
    private String insPriceTermCurrencyCode;
    private String insPriceExchangeRate;

    private String trackingNumber;

    private String orderType;

    @NotNull
    @Column(nullable = false)
    private CrmType crmType;

    @NotNull
    @Column(nullable = false)
    private OrderDetailTable orderDetailTable;

    private long orderDetailTableId;

    @NotNull
    @Column(nullable = false)
    private IteratorTable iteratorTable;

    private long iteratorTableId;

    @NotNull
    @Column(nullable = false)
    private Timestamp created;

    private Timestamp paid;

    private TransactionState transactionState;

    private long shippingTrackingId;

    private ShippingTracking.ShippingTrackingState shippingTrackingState;

    private String note;

    private Timestamp orderDate;
    private String orderId;
    private String orderValue;
    private String customerFirst;
    private String customerLast;
    private String customerCity;
    private String customerProvence;
    // TODO: add postal to transaction
    private String customerCountry;
    private String customerPhone;
    private String customerEmail;
    private int revShare;
    private int revSharePercent;
    private boolean shippingNotifications = false;
    private ShippingNotificationType shippingNotificationType = ShippingNotificationType.NONE;
    private String slug;
    private String courier;
    private StatusTag statusTag;
    private boolean insured;

    public Transaction() {

    }

    public Transaction(long accountId, String apiKey, String crmPushApiKey, String insPrice, String trackingNumber, String orderType, CrmType crmType, OrderDetailTable orderDetailTable, long orderDetailTableId, Timestamp created, TransactionState transactionState) {
        this.accountId = accountId;
        this.apiKey = apiKey;
        this.crmPushApiKey = crmPushApiKey;
        this.insPrice = insPrice;
        this.trackingNumber = trackingNumber;
        this.orderType = orderType;
        this.crmType = crmType;
        this.orderDetailTable = orderDetailTable;
        this.orderDetailTableId = orderDetailTableId;
        this.iteratorTable = IteratorTable.NONE;
        this.created = created;
        this.transactionState = transactionState;
    }

    public Transaction(long accountId, String apiKey, String crmPushApiKey, String insPrice, String trackingNumber, String orderType, CrmType crmType, OrderDetailTable orderDetailTable, long orderDetailTableId, IteratorTable iteratorTable, long iteratorTableId, Timestamp created, TransactionState transactionState) {
        this.accountId = accountId;
        this.apiKey = apiKey;
        this.crmPushApiKey = crmPushApiKey;
        this.insPrice = insPrice;
        this.trackingNumber = trackingNumber;
        this.orderType = orderType;
        this.crmType = crmType;
        this.orderDetailTable = orderDetailTable;
        this.orderDetailTableId = orderDetailTableId;
        this.iteratorTable = iteratorTable;
        this.iteratorTableId = iteratorTableId;
        this.created = created;
        this.transactionState = transactionState;
    }

    public Transaction(long accountId, String apiKey, String crmPushApiKey, String insPrice, String trackingNumber, String orderType, CrmType crmType, OrderDetailTable orderDetailTable, long orderDetailTableId, IteratorTable iteratorTable, long iteratorTableId, Timestamp created, Timestamp paid, TransactionState transactionState, String note) {
        this.accountId = accountId;
        this.apiKey = apiKey;
        this.crmPushApiKey = crmPushApiKey;
        this.insPrice = insPrice;
        this.trackingNumber = trackingNumber;
        this.orderType = orderType;
        this.crmType = crmType;
        this.orderDetailTable = orderDetailTable;
        this.orderDetailTableId = orderDetailTableId;
        this.iteratorTable = iteratorTable;
        this.iteratorTableId = iteratorTableId;
        this.created = created;
        this.paid = paid;
        this.transactionState = transactionState;
        this.note = note;
    }

    public Transaction(long accountId, String apiKey, String crmPushApiKey, String insPrice, String trackingNumber, String orderType, CrmType crmType, OrderDetailTable orderDetailTable, long orderDetailTableId, IteratorTable iteratorTable, long iteratorTableId, Timestamp created, Timestamp paid, TransactionState transactionState, long shippingTrackingId, ShippingTracking.ShippingTrackingState shippingTrackingState, String note) {
        this.accountId = accountId;
        this.apiKey = apiKey;
        this.crmPushApiKey = crmPushApiKey;
        this.insPrice = insPrice;
        this.trackingNumber = trackingNumber;
        this.orderType = orderType;
        this.crmType = crmType;
        this.orderDetailTable = orderDetailTable;
        this.orderDetailTableId = orderDetailTableId;
        this.iteratorTable = iteratorTable;
        this.iteratorTableId = iteratorTableId;
        this.created = created;
        this.paid = paid;
        this.transactionState = transactionState;
        this.shippingTrackingId = shippingTrackingId;
        this.shippingTrackingState = shippingTrackingState;
        this.note = note;
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

    public String getInsPrice() {
        return insPrice;
    }

    public void setInsPrice(String insPrice) {
        this.insPrice = insPrice;
    }

    public String getInsTermPrice() {
        return insTermPrice;
    }

    public void setInsTermPrice(String insTermPrice) {
        this.insTermPrice = insTermPrice;
    }

    public String getInsPriceTermCurrencyCode() {
        return insPriceTermCurrencyCode;
    }

    public void setInsPriceTermCurrencyCode(String insPriceTermCurrencyCode) {
        this.insPriceTermCurrencyCode = insPriceTermCurrencyCode;
    }

    public String getInsPriceExchangeRate() {
        return insPriceExchangeRate;
    }

    public void setInsPriceExchangeRate(String insPriceExchangeRate) {
        this.insPriceExchangeRate = insPriceExchangeRate;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public CrmType getCrmType() {
        return crmType;
    }

    public void setCrmType(CrmType crmType) {
        this.crmType = crmType;
    }

    public OrderDetailTable getOrderDetailTable() {
        return orderDetailTable;
    }

    public void setOrderDetailTable(OrderDetailTable orderDetailTable) {
        this.orderDetailTable = orderDetailTable;
    }

    public long getOrderDetailTableId() {
        return orderDetailTableId;
    }

    public void setOrderDetailTableId(long orderDetailTableId) {
        this.orderDetailTableId = orderDetailTableId;
    }

    public IteratorTable getIteratorTable() {
        return iteratorTable;
    }

    public void setIteratorTable(IteratorTable iteratorTable) {
        this.iteratorTable = iteratorTable;
    }

    public long getIteratorTableId() {
        return iteratorTableId;
    }

    public void setIteratorTableId(long iteratorTableId) {
        this.iteratorTableId = iteratorTableId;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getPaid() {
        return paid;
    }

    public void setPaid(Timestamp paid) {
        this.paid = paid;
    }

    public TransactionState getTransactionState() {
        return transactionState;
    }

    public void setTransactionState(TransactionState transactionState) {
        this.transactionState = transactionState;
    }

    public long getShippingTrackingId() {
        return shippingTrackingId;
    }

    public void setShippingTrackingId(long shippingTrackingId) {
        this.shippingTrackingId = shippingTrackingId;
    }

    public ShippingTracking.ShippingTrackingState getShippingTrackingState() {
        return shippingTrackingState;
    }

    public void setShippingTrackingState(ShippingTracking.ShippingTrackingState shippingTrackingState) {
        this.shippingTrackingState = shippingTrackingState;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(String orderValue) {
        this.orderValue = orderValue;
    }

    public String getCustomerFirst() {
        return customerFirst;
    }

    public void setCustomerFirst(String customerFirst) {
        this.customerFirst = customerFirst;
    }

    public String getCustomerLast() {
        return customerLast;
    }

    public void setCustomerLast(String customerLast) {
        this.customerLast = customerLast;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerProvence() {
        return customerProvence;
    }

    public void setCustomerProvence(String customerProvence) {
        this.customerProvence = customerProvence;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public int getRevShare() {
        return revShare;
    }

    public void setRevShare(int revShare) {
        this.revShare = revShare;
    }

    public int getRevSharePercent() {
        return revSharePercent;
    }

    public void setRevSharePercent(int revSharePercent) {
        this.revSharePercent = revSharePercent;
    }

    public boolean isShippingNotifications() {
        return shippingNotifications;
    }

    public void setShippingNotifications(boolean shippingNotifications) {
        this.shippingNotifications = shippingNotifications;
    }

    public ShippingNotificationType getShippingNotificationType() {
        return shippingNotificationType;
    }

    public void setShippingNotificationType(ShippingNotificationType shippingNotificationType) {
        this.shippingNotificationType = shippingNotificationType;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public StatusTag getStatusTag() {
        return statusTag;
    }

    public void setStatusTag(StatusTag statusTag) {
        this.statusTag = statusTag;
    }

    public boolean isInsured() {
        return insured;
    }

    public void setInsured(boolean insured) {
        this.insured = insured;
    }


}
