package com.moditha.group_finder.exceptions;

public class JwtValidationException extends RuntimeException {
    public JwtValidationException(String message) {
        super(message);
    }

    public JwtValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
