package com.mijatovic.wavealerts.service.firebase.push.implementation;

import com.google.firebase.messaging.*;
import com.mijatovic.wavealerts.model.DirectNotification;
import com.mijatovic.wavealerts.model.Subscription;
import com.mijatovic.wavealerts.model.TopicNotification;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Log4j2
@Service
public class FCMServiceImplementation {

    public void subscribeToTopic(Subscription subscription) {
        try {
            FirebaseMessaging.getInstance().subscribeToTopic(
                    Arrays.asList(subscription.getSubscriber()),
                    subscription.getTopic()
            );
        } catch (FirebaseMessagingException e) {
            log.error("Error subscribing to topic: {}", e.getMessage());
        }
    }

    public void sendNotificationToTarget(DirectNotification notification){
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
