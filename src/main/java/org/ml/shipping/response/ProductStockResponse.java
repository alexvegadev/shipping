package org.ml.shipping.response;

import lombok.Data;
import org.ml.shipping.entity.ProductStock;

@Data
public class ProductStockResponse {
    private int statusCode;
    private String message;
    private ProductStock data;

    public ProductStockResponse(int statusCode, String message, ProductStock data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
}
