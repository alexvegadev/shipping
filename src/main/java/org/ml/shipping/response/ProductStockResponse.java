package org.ml.shipping.response;

import org.ml.shipping.entity.ProductStock;

public class ProductStockResponse {
    private int statusCode;
    private String message;
    private ProductStock data;

    public ProductStockResponse(int statusCode, String message, ProductStock data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ProductStock getData() {
        return data;
    }

    public void setData(ProductStock data) {
        this.data = data;
    }
}
