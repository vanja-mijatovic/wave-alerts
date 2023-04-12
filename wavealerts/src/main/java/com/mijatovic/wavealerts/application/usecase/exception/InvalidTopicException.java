package com.mijatovic.wavealerts.application.usecase.exception;

import com.mijatovic.wavealerts.exception.ModelException;

/**
 * An exception thrown when the input provided to a use case is invalid.
 */
public class InvalidTopicException extends ModelException {

    /**
     * Constructs a new {@code InvalidTopicException} with the specified detail message.
     */
    public InvalidTopicException() {
        super("Invalid topic.");
    }
}
