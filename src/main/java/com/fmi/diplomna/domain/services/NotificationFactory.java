/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.domain.services;

import com.fmi.diplomna.dto.NotificationChannelType;
import com.fmi.diplomna.dto.NotificationContainerDTO;
import com.fmi.diplomna.dto.NotificationDTO;
import com.fmi.diplomna.dto.NotificationDTOBuilder;
import com.fmi.diplomna.dto.NotificationType;
import com.fmi.diplomna.hibernate.AppInfo;
import com.fmi.diplomna.hibernate.NotificationGroup;
import com.fmi.diplomna.hibernate.Server;
import com.fmi.diplomna.hibernate.User;
import com.fmi.diplomna.listeners.StatisticsUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Component;

/**
 *
 * @author killer
 */
@Component
public class NotificationFactory {

    public static NotificationDTO buildResourceUsageNotification(Server server, NotificationType type, CheckType checkType) {
        NotificationDTO dto = new NotificationDTOBuilder()
                .setNotifContainers(buildRecipientsFromServer(server, type))
                .setServerId(String.valueOf(server.getId()))
                .setType(type)
                .setContent(buildContent(type, checkType))
                .setUUID(UUID.randomUUID().toString())
                .createNotificationDTO();
        return dto;
    }
    
    public static NotificationDTO buildAvailabilityNotification(AppInfo server,
                                                                NotificationType type,
                                                                CheckType checkType){
        
        NotificationDTO dto = new NotificationDTOBuilder()
                .setNotifContainers(buildRecipientsFromAppInfo(server, type))
                .setServerId(String.valueOf(server.getId()))
                .setType(type)
                .setContent(buildContent(type, checkType))
                .setUUID(UUID.randomUUID().toString())
                .createNotificationDTO();
        return dto; 
        
    }
    
    private static List<NotificationContainerDTO> buildRecipientsFromNGroup(Set<NotificationGroup> groups, NotificationType type){
        List<NotificationContainerDTO> result = new ArrayList<>(groups.size());
        for (NotificationGroup group : groups) {
            for (User user : group.getUserSet()) {
                result.add(new NotificationContainerDTO(user.getEmail(), NotificationChannelType.EMAIL));
                if(type.equals(NotificationType.CRITICAL)){
                    result.add(new NotificationContainerDTO(user.getPhone(), NotificationChannelType.SMS));
                }
            }
        }
        
        return result;
    }
    
    private static List<NotificationContainerDTO> buildUserRecipients(Set<User> users, NotificationType type){
        List<NotificationContainerDTO> result = new ArrayList<>(users.size());
        for (User user : users) {
            result.add(new NotificationContainerDTO(user.getEmail(), NotificationChannelType.EMAIL));
                if(type.equals(NotificationType.CRITICAL)){
                    result.add(new NotificationContainerDTO(user.getPhone(), NotificationChannelType.SMS));
                }
        }
        return result;
    }
    
    private static List<NotificationContainerDTO> buildRecipientsFromServer(Server server, NotificationType type){
        List<NotificationContainerDTO> recipients = new ArrayList<>();
        recipients.addAll(buildRecipientsFromNGroup(server.getNotificationGroupSet(), type));
        recipients.addAll(buildUserRecipients(server.getUserSet(), type));
        return recipients;
    }
    
        private static List<NotificationContainerDTO> buildRecipientsFromAppInfo(AppInfo server, NotificationType type){
        List<NotificationContainerDTO> recipients = new ArrayList<>();
        recipients.addAll(buildRecipientsFromNGroup(server.getNotificationGroupSet(), type));
        recipients.addAll(buildUserRecipients(server.getUserSet(), type));
        return recipients;
    }
    
    private static String buildContent(NotificationType type, CheckType checkType){
        StringBuilder builder = new StringBuilder();
        builder.append(checkTypeToString(checkType));
        builder.append(" ");
        builder.append(notificatioTypeToString(type));
        return builder.toString();
    }
    
    private static String checkTypeToString(CheckType type){
        String result = "";
        switch(type){
            case CPU:
                result = "CPU";
                break;
            case DISK_USAGE:
                result = "DISK USAGE";
                break;
            case MEMORY:
                result = "MEMORY";
                break;
            case AVAILABILITY:
                result = "AVAILABILITY";
                break;
        }
        return result;
    }
    
    private static String notificatioTypeToString(NotificationType type){
        String result = "";
        switch(type){
            case CRITICAL:
                result= "CRITICAL notification";
                break;
            case WARN:
                result = "WARN notification";
                break;
        }
        
        return result;
    }
        
}
