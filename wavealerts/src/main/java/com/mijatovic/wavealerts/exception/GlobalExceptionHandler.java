package com.mijatovic.wavealerts.exception;

import com.google.api.gax.rpc.NotFoundException;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This class serves as a global exception handler for the application. It extends the ResponseEntityExceptionHandler
 * class and provides methods to handle different types of exceptions.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles NotFoundException by returning an ErrorMessage with the exception message.
     *
     * @param notFoundException the NotFoundException to be handled
     * @return an ErrorMessage with the exception message
     */
    @ExceptionHandler(NotFoundException.class)
    public ErrorMessage notFoundException(NotFoundException notFoundException){
        return new ErrorMessage(notFoundException.getMessage());
    }

    /**
     * Exception handler for SubscriptionFailedException.
     * Returns an error message with the exception message and a bad request HTTP status.
     *
     * @param ex the SubscriptionFailedException that was thrown
     * @return an ErrorMessage object containing the exception message
     */
    @ExceptionHandler(SubscriptionFailedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleSubscriptionFailedException(SubscriptionFailedException ex) {
        return new ErrorMessage(ex.getMessage());
    }

    /**
     * Exception handler method for handling {@link IllegalAccessException}.
     * Returns an HTTP 403 (Forbidden) status code and a message indicating that the user does not have
     * sufficient permissions to access the requested resource.
     *
     * @param ex the {@link IllegalAccessException} object to handle.
     * @return an {@link ErrorMessage} object containing a message indicating that the user does not have sufficient permissions to access the requested resource.
     */
    @ExceptionHandler(IllegalAccessException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorMessage handleIllegalAccessException(IllegalAccessException ex) {
        return new ErrorMessage("You do not have sufficient permissions to access this resource");
    }

}

