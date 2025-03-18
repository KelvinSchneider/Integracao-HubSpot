package com.kelvinSchneider.meetime.exception;

public class InvalidTokenException extends BaseException {

    public InvalidTokenException(String message) {
        super(message);
    }

    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}