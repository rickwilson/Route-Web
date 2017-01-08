package core.entities.client;

import core.entities.ShippingTracking;
import core.entities.ShippingTrackingDetail;
import core.entities.Transaction;
import core.entities.TransactionDetail;
import core.entities.enums.TransactionState;
import core.thirdParty.aftership.enums.StatusTag;
import core.util.CheckData;
import core.util.PrettyDate;

import java.util.ArrayList;

public class ClientTransactionDetailObject {
    // Order
    private String transactionId;
    private String orderId;
    private String orderState;
    private String orderStateColor;
    private String insured;
    private String orderDate;
    private String orderValue;
    private String name;
    private String email;
    private String phone;
    private String city;
    private String province;
    private String postal;
    private String country;

    // Shipping
    private String shippingTag;
    private String shippingTagColor;
    private String trackingId;
    private String courierName;
    private String shipDate;
    private String deliveredDate;

    // Transaction
    private String apiKey;
    private String insPriceBase;
    private String insPriceTerm;
    private String revShareBase;
    private String created;

    // Products
    ArrayList<ClientTransactionDetailProductObject> products;

    // Shipping History
    ArrayList<ShippingTrackingDetail> shippingTrackingDetails;

    // Transaction History
    ArrayList<TransactionDetail> transactionDetails;

    public ClientTransactionDetailObject() {
    }

    public ClientTransactionDetailObject(Transaction transaction, ShippingTracking shippingTracking) {
        created = PrettyDate.makeItPrettyDateOnly(transaction.getCreated());

        transactionId = transaction.getAccountId()+"-"+transaction.getId();
        orderId = transaction.getOrderId();
        orderState = transaction.getTransactionState().getName();
        orderStateColor = "label-success";
        if(transaction.getTransactionState().equals(TransactionState.ERROR) ||
                transaction.getTransactionState().equals(TransactionState.FAILED) ||
                transaction.getTransactionState().equals(TransactionState.VOIDED)) {
            orderStateColor = "label-important";
        } else if(transaction.getTransactionState().equals(TransactionState.INSURANCE_NOT_SELECTED) ||
                transaction.getTransactionState().equals(TransactionState.QUOTE_REQUESTED) ||
                transaction.getTransactionState().equals(TransactionState.TEST)) {
            orderStateColor = "label-warning";
        }
        if(transaction.isInsured()) {
            insured = "true";
        } else {
            insured = "false";
        }
        orderDate = created;
        if(transaction.getOrderDate() != null) {
            orderDate = PrettyDate.makeItPrettyDateOnly(transaction.getOrderDate());
        }
        orderValue = transaction.getOrderValue()+" "+transaction.getInsPriceTermCurrencyCode();
        name = transaction.getCustomerFirst()+" "+transaction.getCustomerLast();
        email = transaction.getCustomerEmail();
        phone = transaction.getCustomerPhone();
        city = transaction.getCustomerCity();
        province = transaction.getCustomerProvence();
        postal = ""; // TODO: add postal to transaction
        country = transaction.getCustomerCountry();
        shippingTag = "Unknown";
        shippingTagColor = "label-warning";
        if(transaction.getStatusTag() != null) {
            shippingTag = transaction.getStatusTag().name();
            if(transaction.getStatusTag().equals(StatusTag.InfoReceived) ||
                    transaction.getStatusTag().equals(StatusTag.InTransit) ||
                    transaction.getStatusTag().equals(StatusTag.OutForDelivery) ||
                    transaction.getStatusTag().equals(StatusTag.Delivered)) {
                shippingTagColor = "label-success";
            } else if(transaction.getStatusTag().equals(StatusTag.Exception) ||
                    transaction.getStatusTag().equals(StatusTag.Expired)) {
                shippingTagColor = "label-important";
            }
        }
        trackingId = transaction.getTrackingNumber();
        courierName = transaction.getCourier();
        shipDate = "Unknown";
        deliveredDate = "Unknown";
        if(shippingTracking!=null) {
            shipDate = PrettyDate.makeItPrettyDateOnly(shippingTracking.getCreated());
            if(shippingTracking.getTag() != null && shippingTracking.getTag().equals(StatusTag.Delivered)) {
                deliveredDate = PrettyDate.makeItPrettyDateOnly(shippingTracking.getLastUpdated());
            } else if(shippingTracking.getTag() != null &&
                    !shippingTracking.getTag().equals(StatusTag.Exception) &&
                    !shippingTracking.getTag().equals(StatusTag.Expired) &&
                    shippingTracking.getExpectedDelivery() != null &&
                    shippingTracking.getExpectedDelivery().trim().length() > 0) {
                deliveredDate = shippingTracking.getExpectedDelivery();
            } else if(shippingTracking.getTag() != null &&
                    (shippingTracking.getTag().equals(StatusTag.Exception) ||
                    shippingTracking.getTag().equals(StatusTag.Expired))) {
                deliveredDate = "failed";
            } else {
                deliveredDate = "Unknown";
            }
        }
        apiKey = transaction.getApiKey().substring(0,9)+"...";
        insPriceBase = transaction.getInsPrice()+" USD";
        insPriceTerm = transaction.getInsTermPrice()+" "+transaction.getInsPriceTermCurrencyCode();
        revShareBase = CheckData.addDecimal(transaction.getRevShare())+" USD";
    }



    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getOrderStateColor() {
        return orderStateColor;
    }

    public void setOrderStateColor(String orderStateColor) {
        this.orderStateColor = orderStateColor;
    }

    public String getInsured() {
        return insured;
    }

    public void setInsured(String insured) {
        this.insured = insured;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(String orderValue) {
        this.orderValue = orderValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getShippingTag() {
        return shippingTag;
    }

    public void setShippingTag(String shippingTag) {
        this.shippingTag = shippingTag;
    }

    public String getShippingTagColor() {
        return shippingTagColor;
    }

    public void setShippingTagColor(String shippingTagColor) {
        this.shippingTagColor = shippingTagColor;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getCourierName() {
        return courierName;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(String deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getInsPriceBase() {
        return insPriceBase;
    }

    public void setInsPriceBase(String insPriceBase) {
        this.insPriceBase = insPriceBase;
    }

    public String getInsPriceTerm() {
        return insPriceTerm;
    }

    public void setInsPriceTerm(String insPriceTerm) {
        this.insPriceTerm = insPriceTerm;
    }

    public String getRevShareBase() {
        return revShareBase;
    }

    public void setRevShareBase(String revShareBase) {
        this.revShareBase = revShareBase;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public ArrayList<ClientTransactionDetailProductObject> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ClientTransactionDetailProductObject> products) {
        this.products = products;
    }

    public ArrayList<ShippingTrackingDetail> getShippingTrackingDetails() {
        return shippingTrackingDetails;
    }

    public void setShippingTrackingDetails(ArrayList<ShippingTrackingDetail> shippingTrackingDetails) {
        this.shippingTrackingDetails = shippingTrackingDetails;
    }

    public ArrayList<TransactionDetail> getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(ArrayList<TransactionDetail> transactionDetails) {
        this.transactionDetails = transactionDetails;
    }
}
