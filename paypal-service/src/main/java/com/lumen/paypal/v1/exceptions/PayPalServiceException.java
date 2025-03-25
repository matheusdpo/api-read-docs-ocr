package com.lumen.paypal.v1.exceptions;

public class PayPalServiceException extends RuntimeException {
    public PayPalServiceException(String message) {
        super(message);
    }
}
