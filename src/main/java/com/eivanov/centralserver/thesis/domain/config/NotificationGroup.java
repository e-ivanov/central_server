/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.domain.config;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author killer
 */
@Document(collection = "notification_group")
public class NotificationGroup {
    
    @Id
    private String id;
    private String serverId;
    private String thresholdTypeId;
    private String notificationTypeId;
    private List<NotificationType> listOfNotifications;

    public NotificationGroup(String serverId, String thresholdTypeId, String notificationTypeId, List<NotificationType> listOfNotifications) {
        this.serverId = serverId;
        this.thresholdTypeId = thresholdTypeId;
        this.notificationTypeId = notificationTypeId;
        this.listOfNotifications = listOfNotifications;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getThresholdTypeId() {
        return thresholdTypeId;
    }

    public void setThresholdTypeId(String thresholdTypeId) {
        this.thresholdTypeId = thresholdTypeId;
    }

    public String getNotificationTypeId() {
        return notificationTypeId;
    }

    public void setNotificationTypeId(String notificationTypeId) {
        this.notificationTypeId = notificationTypeId;
    }

    public List<NotificationType> getListOfNotifications() {
        return listOfNotifications;
    }

    public void setListOfNotifications(List<NotificationType> listOfNotifications) {
        this.listOfNotifications = listOfNotifications;
    }
    
    
    
}
