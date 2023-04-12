package com.mijatovic.wavealerts.model;

import lombok.Data;

/**
 * A POJO class representing a subscription to a Firebase Cloud Messaging topic.
 */
@Data
public class Subscription {

    /**
     * The unique identifier of the subscriber to the topic.
     */
    private String subscriber;

    /**
     * The name of the topic to subscribe to.
     */
    private String topic;
}

