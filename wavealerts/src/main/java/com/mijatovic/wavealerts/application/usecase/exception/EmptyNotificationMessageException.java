package com.mijatovic.wavealerts.application.usecase.exception;

import com.mijatovic.wavealerts.exception.ModelException;

/**
 * An exception thrown when the input provided to a use case is invalid.
 */
public class EmptyNotificationMessageException extends ModelException {

    /**
     * Constructs a new {@code EmptyNotificationMessageException} with the specified detail message.
     */
    public EmptyNotificationMessageException() {
        super("Notification message can not be empty.");
    }
}
