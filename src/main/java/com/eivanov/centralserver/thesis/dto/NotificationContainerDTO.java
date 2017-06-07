/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.dto;

/**
 *
 * @author killer
 */
public class NotificationContainerDTO {
    
    private String target;
    private NotificationChannelType notificationChannel;
    
    public NotificationContainerDTO() {
    }

    public NotificationContainerDTO(String target, NotificationChannelType notificationChannel) {
        this.target = target;
        this.notificationChannel = notificationChannel;
    }


    public String getTarget() {
        return target;
    }

    public NotificationChannelType getNotificationChannel() {
        return notificationChannel;
    }
    
    
}
