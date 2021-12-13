package org.ml.shipping.id;

import java.io.Serializable;

public class ProductId implements Serializable {
    private String productId;
    private String deposit;
    private String location;

    public ProductId(){

    }

    public ProductId(String productId, String deposit, String location) {
        this.productId = productId;
        this.deposit = deposit;
        this.location = location;
    }

    private String getProductId() {
        return productId;
    }

    private void setProductId(String productId) {
        this.productId = productId;
    }

    private String getDeposit() {
        return deposit;
    }

    private void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    private String getLocation() {
        return location;
    }

    private void setLocation(String location) {
        this.location = location;
    }
}
