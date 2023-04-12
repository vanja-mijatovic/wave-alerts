package com.mijatovic.wavealerts.application.usecase.exception;

import com.mijatovic.wavealerts.exception.ModelException;

/**
 * An exception thrown when the input provided to a use case is invalid.
 */
public class EmptyNotificationTitleException extends ModelException {

    /**
     * Constructs a new {@code EmptyNotificationTitleException} with the specified detail message.
     */
    public EmptyNotificationTitleException() {
        super("Notification title can not be empty.");
    }
}
