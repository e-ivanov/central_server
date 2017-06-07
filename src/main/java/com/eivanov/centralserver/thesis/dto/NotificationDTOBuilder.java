/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.dto;

import java.util.List;


public class NotificationDTOBuilder {

    private List<NotificationChannelType> notificationChannels;
    private String content;
    private List<String> recepients;
    private String serverId;
    private NotificationType type;
    private List<NotificationContainerDTO> notifContainers;
    private String uuid;

    public NotificationDTOBuilder() {
    }

    public NotificationDTOBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public NotificationDTOBuilder setRecepients(List<String> recepients) {
        this.recepients = recepients;
        return this;
    }

    public NotificationDTOBuilder setServerId(String serverId) {
        this.serverId = serverId;
        return this;
    }

    public NotificationDTOBuilder setType(NotificationType type) {
        this.type = type;
        return this;
    }

    public NotificationDTOBuilder setNotifContainers(List<NotificationContainerDTO> notifContainers) {
        this.notifContainers = notifContainers;
        return this;
    }
    
    public NotificationDTOBuilder setUUID(String uuid){
        this.uuid = uuid;
        return this;
    }

    public NotificationDTO createNotificationDTO() {
        return new NotificationDTO(content, recepients, serverId, type, notifContainers, uuid);
    }
    
}
