package com.mijatovic.wavealerts.service.firebase.push.implementation;

import com.google.firebase.messaging.*;
import com.mijatovic.wavealerts.exception.SubscriptionFailedException;
import com.mijatovic.wavealerts.model.DirectNotification;
import com.mijatovic.wavealerts.model.Subscription;
import com.mijatovic.wavealerts.model.TopicNotification;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Service class that provides methods for interacting with Firebase Cloud Messaging (FCM) service.
 */
@Service
public class FCMServiceImplementation {

    /**
     * Subscribes a user to FCM topic.
     *
     * @param subscription The subscription details, including the subscriber and topic.
     * @throws SubscriptionFailedException If the subscription fails due to an error in the FirebaseMessaging service.
     */
    public void subscribeToTopic(Subscription subscription) {
        try {
            FirebaseMessaging.getInstance().subscribeToTopic(
                    Arrays.asList(subscription.getSubscriber()),
                    subscription.getTopic()
            );
        } catch (FirebaseMessagingException e) {
            throw new SubscriptionFailedException(e.getMessage());
        }
    }

    /**
     * Sends a direct notification to a specific FCM token.
     *
     * @param notification The notification details, including the target token, title, and message.
     * @throws FirebaseMessagingException If there is an error in the FirebaseMessaging service while sending the notification.
     */
    public void sendNotificationToTarget(DirectNotification notification) {
        Message message = Message.builder()
                .setWebpushConfig(
                        WebpushConfig.builder()
                                .setNotification(
                                        WebpushNotification.builder()
                                                .setTitle(notification.getTitle())
                                                .setBody(notification.getMessage())
                                                .setIcon("https://assets.mapquestapi.com/icon/v2/circle@2x.png")
                                                .build()
                                ).build()
                )
                .setToken(notification.getTarget())
                .build();
        FirebaseMessaging.getInstance().sendAsync(message);
    }

    /**
     * Sends a notification to a FCM topic.
     *
     * @param notification The notification details, including the topic, title, and message.
     * @throws FirebaseMessagingException If there is an error in the FirebaseMessaging service while sending the notification.
     */
    public void sendNotificationToTopic(TopicNotification notification) {
        Message message = Message.builder()
                .setWebpushConfig(WebpushConfig.builder()
                        .setNotification(WebpushNotification.builder()
                                .setTitle(notification.getTitle())
                                .setBody(notification.getMessage())
                                .setIcon("https://assets.mapquestapi.com/icon/v2/incident@2x.png")
                                .build())
                        .build())
                .setTopic(notification.getTopic())
                .build();

        FirebaseMessaging.getInstance().sendAsync(message);
    }
}
