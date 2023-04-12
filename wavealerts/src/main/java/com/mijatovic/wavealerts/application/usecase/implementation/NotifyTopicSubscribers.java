package com.mijatovic.wavealerts.application.usecase.implementation;

import com.mijatovic.wavealerts.application.usecase.exception.EmptyNotificationMessageException;
import com.mijatovic.wavealerts.application.usecase.exception.EmptyNotificationTitleException;
import com.mijatovic.wavealerts.application.usecase.exception.InvalidTargetTokenException;
import com.mijatovic.wavealerts.application.usecase.interfaces.UseCase;
import com.mijatovic.wavealerts.model.TopicNotification;
import com.mijatovic.wavealerts.service.firebase.push.implementation.FCMServiceImplementation;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

/**
 * Use case to notify all subscribers of a specific topic with a given notification.
 */
@Service
@AllArgsConstructor
public class NotifyTopicSubscribers implements UseCase<NotifyTopicSubscribers.NotifyTopicSubscribersInput> {

    private final FCMServiceImplementation fcmServiceImplementation;

    /**
     * Input values for the use case.
     */
    @Value
    @AllArgsConstructor(staticName = "of")
    public static class NotifyTopicSubscribersInput implements UseCase.InputValues{
        private final TopicNotification topicNotification;
    }

    /**
     * Executes the use case.
     *
     * @param input the input values for the use case
     * @throws InvalidTargetTokenException if the topic in the input is null
     * @throws EmptyNotificationTitleException if the title in the input is null
     * @throws EmptyNotificationMessageException if the message in the input is null
     */
    @Override
    public void execute(NotifyTopicSubscribersInput input) {
        validateNotNull(input);
        validateInputFields(input.getTopicNotification());
        fcmServiceImplementation.sendNotificationToTopic(input.getTopicNotification());
    }

    /**
     * Validates the input fields of the given {@link TopicNotification} object.
     *
     * @param notification the notification to validate
     * @throws InvalidTargetTokenException   if the notification topic is null
     * @throws EmptyNotificationTitleException if the notification title is null
     * @throws EmptyNotificationMessageException if the notification message is null
     */
    private void validateInputFields(TopicNotification notification) {
        if (notification.getTopic() == null)
            throw new InvalidTargetTokenException();
        if (notification.getTitle() == null)
            throw new EmptyNotificationTitleException();
        if (notification.getMessage() == null)
            throw new EmptyNotificationMessageException();
    }


}

