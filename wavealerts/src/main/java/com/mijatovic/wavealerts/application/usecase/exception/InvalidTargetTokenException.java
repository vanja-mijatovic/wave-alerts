package com.mijatovic.wavealerts.application.usecase.exception;

import com.mijatovic.wavealerts.exception.ModelException;

/**
 * An exception thrown when the input provided to a use case is invalid.
 */
public class InvalidTargetTokenException extends ModelException {

    /**
     * Constructs a new {@code InvalidTargetTokenException} with the specified detail message.
     */
    public InvalidTargetTokenException() {
        super("Invalid target token.");
    }
}
