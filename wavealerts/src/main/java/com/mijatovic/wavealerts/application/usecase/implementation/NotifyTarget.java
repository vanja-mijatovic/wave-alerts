package com.mijatovic.wavealerts.application.usecase.implementation;

import com.mijatovic.wavealerts.application.usecase.exception.EmptyNotificationMessageException;
import com.mijatovic.wavealerts.application.usecase.exception.EmptyNotificationTitleException;
import com.mijatovic.wavealerts.application.usecase.exception.InvalidTargetTokenException;
import com.mijatovic.wavealerts.application.usecase.interfaces.UseCase;
import com.mijatovic.wavealerts.model.DirectNotification;
import com.mijatovic.wavealerts.service.firebase.push.implementation.FCMServiceImplementation;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

/**
 * Use case to notify target with a given notification.
 */
@Service
@AllArgsConstructor
public class NotifyTarget implements UseCase<NotifyTarget.NotifyTargetInput>{

    private final FCMServiceImplementation fcmServiceImplementation;

    /**
     * Represents the input values required for executing the UseCase.
     */
    @Value
    @AllArgsConstructor(staticName = "of")
    public static class NotifyTargetInput implements UseCase.InputValues{
        private final DirectNotification directNotification;
    }

    /**
     * Executes the UseCase with the provided input.
     * Sends a notification to a specific device token.
     * @param input the input values required for executing the UseCase.
     * @throws InvalidTargetTokenException if the target token in the notification is null.
     * @throws EmptyNotificationTitleException if the title in the notification is null.
     * @throws EmptyNotificationMessageException if the message in the notification is null.
     */
    @Override
    public void execute(NotifyTargetInput input) {
        validateNotNull(input);
        validateInputFields(input.getDirectNotification());
        fcmServiceImplementation.sendNotificationToTarget(input.getDirectNotification());
    }

    /**
     * Validates the input fields of the DirectNotification object.
     * @param notification the notification object to validate.
     * @throws InvalidTargetTokenException if the target token in the notification is null.
     * @throws EmptyNotificationTitleException if the title in the notification is null.
     * @throws EmptyNotificationMessageException if the message in the notification is null.
     */
    private void validateInputFields(DirectNotification notification){
        if(notification.getTarget() == null)
            throw new InvalidTargetTokenException();
        if(notification.getTitle() == null)
            throw new EmptyNotificationTitleException();
        if(notification.getMessage() == null)
            throw new EmptyNotificationMessageException();
    }

}

