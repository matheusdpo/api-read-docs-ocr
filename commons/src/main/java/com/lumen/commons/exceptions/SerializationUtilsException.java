package com.lumen.commons.exceptions;

public class SerializationUtilsException extends Exception {

    public SerializationUtilsException() {
        super("An error occurred while processing the request.");
    }

    public SerializationUtilsException(String message) {
        super(message);
    }

    public SerializationUtilsException(String message, Throwable cause) {
        super(message, cause);
    }
}
