package com.mijatovic.wavealerts.controller;

import com.mijatovic.wavealerts.application.usecase.implementation.NotifyTarget;
import com.mijatovic.wavealerts.application.usecase.implementation.NotifyTopicSubscribers;
import com.mijatovic.wavealerts.application.usecase.implementation.Subscribe;
import com.mijatovic.wavealerts.model.DirectNotification;
import com.mijatovic.wavealerts.model.Subscription;
import com.mijatovic.wavealerts.model.TopicNotification;
import com.mijatovic.wavealerts.service.firebase.push.implementation.FCMServiceImplementation;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller class exposes endpoints to send Firebase push notifications to devices or topics, as well as
 * subscribe a device to a topic.
 */
@AllArgsConstructor
@RestController
public class FirebasePushNotificationController {

    private final NotifyTarget notifyTargetUseCase;
    private final NotifyTopicSubscribers notifyTopicSubscribersUseCase;
    private final Subscribe subscribeUseCase;

    /**
     * Sends a direct notification to a specific device identified by its FCM registration token.
     *
     * @param notification The notification to be sent.
     */
    @PostMapping("/notification")
    public void sendTargetedNotification(@RequestBody DirectNotification notification) {
        notifyTargetUseCase.execute(NotifyTarget.NotifyTargetInput.of(notification));
    }

    /**
     * Sends a notification to a topic. All devices subscribed to this topic will receive the notification.
     *
     * @param notification The notification to be sent.
     */
    @PostMapping("/topic/notification")
    public void sendNotificationToTopic(@RequestBody TopicNotification notification) {
        notifyTopicSubscribersUseCase.execute(NotifyTopicSubscribers.NotifyTopicSubscribersInput.of(notification));
    }

    /**
     * Subscribes a device to a topic.
     *
     * @param subscription The subscription details, containing the device's FCM registration token and the topic to subscribe to.
     */
    @PostMapping("/topic/subscription")
    public void subscribeToTopic(@RequestBody Subscription subscription) {
        subscribeUseCase.execute(Subscribe.SubscribeInput.of(subscription));
    }
}

