package com.mijatovic.wavealerts.model;

import lombok.Data;

/**
 * A class representing a notification message with a title and a message.
 */
@Data
public class Notification {
    /**
     * The title of the notification.
     */
    private String title;

    /**
     * The message of the notification.
     */
    private String message;
}

