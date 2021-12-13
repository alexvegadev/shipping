package org.ml.shipping.exception;

public class DepositLimitException extends Exception{
    public DepositLimitException() {
        super("El depósito no permite más de 3 tipos de productos distintos.");
    }
}
