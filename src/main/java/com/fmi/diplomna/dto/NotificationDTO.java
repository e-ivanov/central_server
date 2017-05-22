/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.dto;

import java.util.List;

/**
 *
 * @author killer
 */
public class NotificationDTO {
    
    private String content;
    private String serverId;
    private List<NotificationContainerDTO> notificationContainers;
    private NotificationType type;

    public NotificationDTO() {
    }

    public NotificationDTO( String content,
                           List<String> recepients, String serverId, NotificationType type,
                           List<NotificationContainerDTO> notifContainers) {
        this.content = content;
        this.serverId = serverId;
        this.type = type;
        this.notificationContainers = notifContainers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<NotificationContainerDTO> getNotificationContainers() {
        return notificationContainers;
    }

    public void setNotificationContainers(List<NotificationContainerDTO> notificationContainers) {
        this.notificationContainers = notificationContainers;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "NotificationDTO{" + "content=" + content + ", serverId=" + serverId + ", notificationContainers=" + notificationContainers + ", type=" + type + '}';
    }
    
}
