package core.entities.client;

import core.entities.ShippingTracking;
import core.entities.Transaction;
import core.entities.enums.TransactionState;
import core.thirdParty.aftership.enums.StatusTag;
import core.util.CheckData;
import core.util.PrettyDate;
import core.util.ShipConverter;

public class ClientReportItemObject {

    private long transactionId;
    private String orderDate;
    private String orderId;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private String customerCity;
    private String customerProvence;
    private String customerCountry;
    private String orderValue;
    private boolean insured;
    private int revShare;
    private String courier;
    private String trackingNumber;
    private StatusTag shipStatusTag;
    private ShippingTracking.ShippingTrackingState shippingTrackingState;
    private TransactionState transactionState;
    private String destination;

    public ClientReportItemObject() {
    }

    public ClientReportItemObject(Transaction transaction) {
        this.transactionId = transaction.getId();
        this.orderDate = "";
        if(transaction.getOrderDate() != null) {
            String tryPrettyOrderDate = PrettyDate.makeItPrettyDateOnly(transaction.getOrderDate());
            if(tryPrettyOrderDate != null && tryPrettyOrderDate.trim().length() > 1) {
                this.orderDate = tryPrettyOrderDate;
            }
        }
        if(orderDate.trim().length() < 1) {
            String tryPrettyOrderDate = PrettyDate.makeItPrettyDateOnly(transaction.getCreated());
            if(tryPrettyOrderDate != null && tryPrettyOrderDate.trim().length() > 1) {
                this.orderDate = tryPrettyOrderDate;
            }
        }
        this.orderId = transaction.getOrderId();
        this.customerName = transaction.getCustomerFirst()+" "+transaction.getCustomerLast();
        this.customerPhone = transaction.getCustomerPhone();
        this.customerEmail = transaction.getCustomerEmail();
        this.customerCity = transaction.getCustomerCity();
        this.customerProvence = transaction.getCustomerProvence();
        this.customerCountry = transaction.getCustomerCountry();
        this.orderValue = transaction.getOrderValue();
        this.insured = transaction.isInsured();
        this.revShare = transaction.getRevShare();
        this.trackingNumber = transaction.getTrackingNumber();
        this.shipStatusTag = transaction.getStatusTag();
        this.courier = transaction.getCourier();
        this.shippingTrackingState = transaction.getShippingTrackingState();
        this.transactionState = transaction.getTransactionState();
        this.destination = transaction.getCustomerProvence()+", "+transaction.getCustomerCountry();
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(String orderValue) {
        this.orderValue = orderValue;
    }

    public boolean isInsured() {
        return insured;
    }

    public void setInsured(boolean insured) {
        this.insured = insured;
    }

    public int getRevShare() {
        return revShare;
    }

    public void setRevShare(int revShare) {
        this.revShare = revShare;
    }

    public String getRevShareFormatted() {
        return CheckData.addDecimal(revShare);
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public StatusTag getShipStatusTag() {
        return shipStatusTag;
    }

    public void setShipStatusTag(StatusTag shipStatusTag) {
        this.shipStatusTag = shipStatusTag;
    }

    public String getshipStatusTagFormatted() {
        return ShipConverter.convertStatusTag(shipStatusTag);
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public ShippingTracking.ShippingTrackingState getShippingTrackingState() {
        return shippingTrackingState;
    }

    public void setShippingTrackingState(ShippingTracking.ShippingTrackingState shippingTrackingState) {
        this.shippingTrackingState = shippingTrackingState;
    }

    public TransactionState getTransactionState() {
        return transactionState;
    }

    public void setTransactionState(TransactionState transactionState) {
        this.transactionState = transactionState;
    }

    public String getTransactionStateName() {
        return this.transactionState.getName();
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
