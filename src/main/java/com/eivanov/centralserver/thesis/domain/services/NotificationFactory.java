/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.domain.services;

import com.eivanov.centralserver.thesis.domain.mongo.SensorReading;
import com.eivanov.centralserver.thesis.dto.NotificationChannelType;
import com.eivanov.centralserver.thesis.dto.NotificationContainerDTO;
import com.eivanov.centralserver.thesis.dto.NotificationDTO;
import com.eivanov.centralserver.thesis.dto.NotificationDTOBuilder;
import com.eivanov.centralserver.thesis.dto.NotificationType;
import com.eivanov.centralserver.thesis.entity.AppInfo;
import com.eivanov.centralserver.thesis.entity.NotificationGroup;
import com.eivanov.centralserver.thesis.entity.Server;
import com.eivanov.centralserver.thesis.entity.User;
import com.eivanov.centralserver.thesis.listeners.StatisticsUtils;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

/**
 *
 * @author killer
 */
@Component
public class NotificationFactory {

    public static NotificationDTO buildResourceUsageNotification(Server server,
            NotificationType type,
            NotificationData info,
            float avgUsage,
            List<SensorReading> readings) {
        NotificationDTO dto = new NotificationDTOBuilder()
                .setNotifContainers(buildRecipientsFromServer(server, type))
                .setServerId(String.valueOf(server.getId()))
                .setType(type)
                .setContent(buildContentForResourceUsage(type,  
                        avgUsage, info, readings))
                .setUUID(UUID.randomUUID().toString())
                .createNotificationDTO();
        return dto;
    }

    public static NotificationDTO buildAvailabilityNotification(AppInfo server,
            NotificationType type,
            CheckType checkType) {

        NotificationDTO dto = new NotificationDTOBuilder()
                .setNotifContainers(buildRecipientsFromAppInfo(server, type))
                .setServerId(String.valueOf(server.getId()))
                .setType(type)
                .setContent(buildContentForAvailability(type, checkType))
                .setUUID(UUID.randomUUID().toString())
                .createNotificationDTO();
        return dto;

    }

    private static List<NotificationContainerDTO> buildRecipientsFromNGroup(Set<NotificationGroup> groups, NotificationType type) {
        List<NotificationContainerDTO> result = new ArrayList<>(groups.size());
        for (NotificationGroup group : groups) {
            for (User user : group.getUserSet()) {
                result.add(new NotificationContainerDTO(user.getEmail(), NotificationChannelType.EMAIL));
                if (type.equals(NotificationType.CRITICAL)) {
                    result.add(new NotificationContainerDTO(user.getPhone(), NotificationChannelType.SMS));
                }
            }
        }

        return result;
    }

    private static List<NotificationContainerDTO> buildUserRecipients(Set<User> users, NotificationType type) {
        List<NotificationContainerDTO> result = new ArrayList<>(users.size());
        for (User user : users) {
            result.add(new NotificationContainerDTO(user.getEmail(), NotificationChannelType.EMAIL));
            if (type.equals(NotificationType.CRITICAL)) {
                result.add(new NotificationContainerDTO(user.getPhone(), NotificationChannelType.SMS));
            }
        }
        return result;
    }

    private static List<NotificationContainerDTO> buildRecipientsFromServer(Server server, NotificationType type) {
        List<NotificationContainerDTO> recipients = new ArrayList<>();
        recipients.addAll(buildRecipientsFromNGroup(server.getNotificationGroupSet(), type));
        recipients.addAll(buildUserRecipients(server.getUserSet(), type));
        return recipients;
    }

    private static List<NotificationContainerDTO> buildRecipientsFromAppInfo(AppInfo server, NotificationType type) {
        List<NotificationContainerDTO> recipients = new ArrayList<>();
        recipients.addAll(buildRecipientsFromNGroup(server.getNotificationGroupSet(), type));
        recipients.addAll(buildUserRecipients(server.getUserSet(), type));
        return recipients;
    }

    private static String buildContentForResourceUsage(NotificationType type,
            float avgUsage,
            NotificationData info,
            List<SensorReading> readings) {
        
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
	velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();
        Template t = velocityEngine.getTemplate("vm_templates/notification.vm");
        VelocityContext context = new VelocityContext();
        context.put("name", "World");
        context.put("email", "test@example.com");
        context.put("checkType", checkTypeToString(info.getType()));
        context.put("notificationType", notificatioTypeToString(type));
        context.put("avgUsage", avgUsage);
        context.put("warnUsage", info.getNotificationConfig().getWarnLevel());
        context.put("maxLoad", info.getNotificationConfig().getCriticalLevel());
        context.put("currentDate", DateTime.now());

        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        writer.flush();
        return writer.toString();
    }

    private static String buildContentForAvailability(NotificationType type,
            CheckType checkType) {
        StringBuilder builder = new StringBuilder();
        builder.append(checkTypeToString(checkType));
        builder.append(" ");
        builder.append(notificatioTypeToString(type));
        return builder.toString();
    }

    private static String checkTypeToString(CheckType type) {
        String result = "";
        switch (type) {
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

    private static String notificatioTypeToString(NotificationType type) {
        String result = "";
        switch (type) {
            case CRITICAL:
                result = "CRITICAL notification";
                break;
            case WARN:
                result = "WARN notification";
                break;
        }

        return result;
    }

}
