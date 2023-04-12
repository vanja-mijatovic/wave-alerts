package com.mijatovic.wavealerts.model;

import lombok.Data;

/**
 * Represents a notification that is sent to a Firebase Cloud Messaging topic.
 */
@Data
public class TopicNotification extends Notification {
    /**
     * The topic to which the notification should be sent.
     */
    private String topic;
}

