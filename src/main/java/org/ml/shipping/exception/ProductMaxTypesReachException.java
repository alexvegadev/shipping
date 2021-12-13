package org.ml.shipping.exception;

public class ProductMaxTypesReachException extends Exception {
    public ProductMaxTypesReachException() {
        super("El depósito no permite más de 3 tipos de productos distintos.");
    }
}
