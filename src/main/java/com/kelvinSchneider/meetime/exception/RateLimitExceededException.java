package com.kelvinSchneider.meetime.exception;

public class RateLimitExceededException extends BaseException {

    public RateLimitExceededException(String message) {
        super(message);
    }

    public RateLimitExceededException(String message, Throwable cause) {
        super(message, cause);
    }
}