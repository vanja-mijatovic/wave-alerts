package com.mijatovic.wavealerts.service.firebase.push.Interfaces;

import com.mijatovic.wavealerts.model.DirectNotification;
import com.mijatovic.wavealerts.model.Subscription;
import com.mijatovic.wavealerts.model.TopicNotification;

public interface FCMService {
    void subscribeToTopic(Subscription subscription);
    void sendNotificationToTarget(DirectNotification notification);
    void sendNotificationToTopic(TopicNotification notification);
}
