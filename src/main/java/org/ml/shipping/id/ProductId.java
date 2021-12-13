package org.ml.shipping.id;

import lombok.Data;

import java.io.Serializable;

@Data
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
}
