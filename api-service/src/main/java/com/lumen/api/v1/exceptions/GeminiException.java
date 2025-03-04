package com.lumen.api.v1.exceptions;

public class GeminiException extends Exception {

    public GeminiException() {
        super("An error occurred while processing the request.");
    }

    public GeminiException(String message) {
        super(message);
    }

    public GeminiException(String message, Throwable cause) {
        super(message, cause);
    }
}
