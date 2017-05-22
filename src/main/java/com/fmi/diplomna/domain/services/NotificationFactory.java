/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.domain.services;

import com.fmi.diplomna.dto.NotificationChannel;
import com.fmi.diplomna.dto.NotificationContainerDTO;
import com.fmi.diplomna.dto.NotificationDTO;
import com.fmi.diplomna.dto.NotificationDTOBuilder;
import com.fmi.diplomna.dto.NotificationType;
import com.fmi.diplomna.hibernate.NotificationGroup;
import com.fmi.diplomna.hibernate.Server;
import com.fmi.diplomna.hibernate.User;
import com.fmi.diplomna.listeners.StatisticsUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author killer
 */
public class NotificationFactory {

    public static NotificationDTO buildResourceUsageNotification(Server server, NotificationType type, CheckType checkType) {
        NotificationDTO dto = new NotificationDTOBuilder()
                .setNotifContainers(buildRecipients(server, type))
                .setServerId(String.valueOf(server.getId()))
                .setType(type)
                .setContent(buildContent(type, checkType))
                .createNotificationDTO();
        return dto;
    }
    
//    public static NotificationDTO buildAvailabilityNotification(String serverUrl,
//                                                                NotificationType type,
//                                                                CheckType type){
//        
//    }

    private static List<NotificationContainerDTO> buildRecipients(Server server, NotificationType type) {
        List<NotificationContainerDTO> recipients = new ArrayList<>();
        for (NotificationGroup group : server.getNotificationGroupSet()) {
            for (User user : group.getUserSet()) {
                recipients.add(new NotificationContainerDTO(user.getEmail(), NotificationChannel.EMAIL));
                if(type.equals(NotificationType.CRITICAL)){
                    recipients.add(new NotificationContainerDTO(user.getPhone(), NotificationChannel.SMS));
                }
            }
        }

        for (User user : server.getUserSet()) {
            recipients.add(new NotificationContainerDTO(user.getEmail(), NotificationChannel.EMAIL));
                if(type.equals(NotificationType.CRITICAL)){
                    recipients.add(new NotificationContainerDTO(user.getPhone(), NotificationChannel.SMS));
                }
        }
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
