package org.ml.shipping.exception;

public final class ProductNotFoundException extends Exception{

    public ProductNotFoundException() {
        super("El producto no pudo ser encontrado.");
    }

}
