package org.ml.shipping.exception;

public class ProductNotValidException extends Exception {
    public ProductNotValidException(int max) {
        super(String.format("El depósito llegó al máximo permitido (%d)", max));
    }
}
