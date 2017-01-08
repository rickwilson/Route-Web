package core.entities.client;

import core.entities.KonnektiveOrderDetailsItem;

public class ClientTransactionDetailProductObject {
    private String productName;
    private String productId;
    private String termTotalPrice;
    private String quantity;
    private String description;

    public ClientTransactionDetailProductObject() {
    }

    public ClientTransactionDetailProductObject(KonnektiveOrderDetailsItem konnektiveOrderDetailsItem) {
        productName = konnektiveOrderDetailsItem.getName();
        productId = konnektiveOrderDetailsItem.getProductId();
        termTotalPrice = konnektiveOrderDetailsItem.getPrice();
        quantity = konnektiveOrderDetailsItem.getProductQty();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTermTotalPrice() {
        return termTotalPrice;
    }

    public void setTermTotalPrice(String termTotalPrice) {
        this.termTotalPrice = termTotalPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
