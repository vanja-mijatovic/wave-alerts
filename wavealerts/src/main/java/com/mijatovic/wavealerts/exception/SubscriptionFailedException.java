package com.mijatovic.wavealerts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to be thrown when subscribing to a topic fails.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SubscriptionFailedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new SubscriptionFailedException with the specified detail message.
     *
     * @param message the detail message
     */
    public SubscriptionFailedException(String message) {
        super(message);
    }

}

