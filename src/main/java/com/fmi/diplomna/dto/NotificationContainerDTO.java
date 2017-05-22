/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.dto;

/**
 *
 * @author killer
 */
public class NotificationContainerDTO {
    
    private final String target;
    private final NotificationChannel notificationChannel;

    public NotificationContainerDTO(String target, NotificationChannel notificationChannel) {
        this.target = target;
        this.notificationChannel = notificationChannel;
    }


    public String getTarget() {
        return target;
    }

    public NotificationChannel getNotificationChannel() {
        return notificationChannel;
    }
    
    
}
