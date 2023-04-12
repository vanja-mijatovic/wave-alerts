package com.mijatovic.wavealerts.application.usecase.implementation;

import com.mijatovic.wavealerts.application.usecase.exception.InvalidTargetTokenException;
import com.mijatovic.wavealerts.application.usecase.exception.InvalidTopicException;
import com.mijatovic.wavealerts.application.usecase.interfaces.UseCase;
import com.mijatovic.wavealerts.model.Subscription;
import com.mijatovic.wavealerts.service.firebase.push.implementation.FCMServiceImplementation;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

/**
 * Use case to subscribe user to a specific topic with.
 */
@Service
@AllArgsConstructor
public class Subscribe implements UseCase<Subscribe.SubscribeInput> {

    private final FCMServiceImplementation fcmServiceImplementation;

    /**
     * Represents the input values required for the Subscribe use case.
     */
    @Value
    @AllArgsConstructor(staticName = "of")
    public static class SubscribeInput implements UseCase.InputValues{
        private final Subscription subscription;
    }

    /**
     * Executes the Subscribe use case.
     *
     * @param input the input values for the use case.
     * @throws InvalidTargetTokenException if the subscriber field in the subscription is null.
     * @throws InvalidTopicException if the topic field in the subscription is null.
     */
    @Override
    public void execute(SubscribeInput input) {
        validateNotNull(input);
        validateInputFields(input.getSubscription());
        fcmServiceImplementation.subscribeToTopic(input.getSubscription());
    }

    /**
     * Validates the input fields in the subscription.
     *
     * @param subscription the subscription to validate.
     * @throws InvalidTargetTokenException if the subscriber field in the subscription is null.
     * @throws InvalidTopicException if the topic field in the subscription is null.
     */
    private void validateInputFields(Subscription subscription){
        if(subscription.getSubscriber() == null)
            throw new InvalidTargetTokenException();
        if(subscription.getTopic() == null)
            throw new InvalidTopicException();
    }

}

