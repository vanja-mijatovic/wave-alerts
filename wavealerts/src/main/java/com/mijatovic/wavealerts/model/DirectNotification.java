package com.mijatovic.wavealerts.model;

import lombok.Data;

/**
 * A class representing a direct notification.
 */
@Data
public class DirectNotification extends Notification {
    /**
     * The target of the notification.
     */
    private String target;
}

