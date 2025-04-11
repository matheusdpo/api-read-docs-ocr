package com.lumen.commons.exceptions;

public class PayPalServiceException extends RuntimeException {
    public PayPalServiceException(String message) {
        super(message);
    }
}
