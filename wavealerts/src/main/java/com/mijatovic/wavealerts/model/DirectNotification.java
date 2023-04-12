package com.mijatovic.wavealerts.model;

import lombok.Data;

@Data
public class DirectNotification extends Notification {
    private String target;
}
