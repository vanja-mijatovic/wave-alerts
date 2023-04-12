package com.mijatovic.wavealerts.service.firebase.push.Interfaces;

import com.mijatovic.wavealerts.exception.SubscriptionFailedException;
import com.mijatovic.wavealerts.model.DirectNotification;
import com.mijatovic.wavealerts.model.Subscription;
import com.mijatovic.wavealerts.model.TopicNotification;

/**
 * An interface for Firebase Cloud Messaging service
 */
public interface FCMService {

    /**
     * Subscribes a user to a given topic
     *
     * @param subscription the Subscription object containing the subscriber's ID and the topic name
     * @throws SubscriptionFailedException if there was an error while subscribing the user to the topic
     */
    void subscribeToTopic(Subscription subscription);

    /**
     * Sends a notification directly to a specific target device
     *
     * @param notification the DirectNotification object containing the target device ID and notification details
     */
    void sendNotificationToTarget(DirectNotification notification);

    /**
     * Sends a notification to all devices subscribed to a given topic
     *
     * @param notification the TopicNotification object containing the topic name and notification details
     */
    void sendNotificationToTopic(TopicNotification notification);
}

