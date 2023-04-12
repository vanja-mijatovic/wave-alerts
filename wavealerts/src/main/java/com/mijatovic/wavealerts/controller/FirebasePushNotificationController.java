package com.mijatovic.wavealerts.controller;

import com.mijatovic.wavealerts.model.DirectNotification;
import com.mijatovic.wavealerts.model.Subscription;
import com.mijatovic.wavealerts.model.TopicNotification;
import com.mijatovic.wavealerts.service.firebase.push.implementation.FCMServiceImplementation;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class FirebasePushNotificationController {
    private final FCMServiceImplementation fcmServiceImplementation;

    @PostMapping("/notification")
    public void sendTargetedNotification(@RequestBody DirectNotification notification) {
        fcmServiceImplementation.sendNotificationToTarget(notification);
    }

    @PostMapping("/topic/notification")
    public void sendNotificationToTopic(@RequestBody TopicNotification notification) {
        fcmServiceImplementation.sendNotificationToTopic(notification);
    }

    @PostMapping("/topic/subscription")
    public void subscribeToTopic(@RequestBody Subscription subscription) {
        fcmServiceImplementation.subscribeToTopic(subscription);
    }
}
