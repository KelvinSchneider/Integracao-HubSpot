package com.kelvinSchneider.meetime.exception;

public class WebhookException extends BaseException {

    public WebhookException(String message) {
        super(message);
    }

    public WebhookException(String message, Throwable cause) {
        super(message, cause);
    }
}
