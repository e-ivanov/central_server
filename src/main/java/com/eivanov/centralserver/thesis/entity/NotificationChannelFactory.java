/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.entity;

import com.eivanov.centralserver.thesis.dto.NotificationChannelType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author killer
 */
public class NotificationChannelFactory {

    private NotificationChannelFactory() {
        throw new IllegalAccessError("Utility class");
    }

    public static NotificationChannel createNotificationChannel(final HttpServletRequest request) {
        final NotificationChannelType type = NotificationChannelType.valueOf(request.getParameter("type"));

        switch (type) {
            case EMAIL:
                return createEmailNotification(request, type);
            case SMS:
            default:
                return null;

        }

    }

    private static void setCommonParameters(final HttpServletRequest request,
            NotificationChannel channel,
            NotificationChannelType type) {
        if (request.getParameter("id") != null) {
            channel.setId(Long.parseLong(request.getParameter("id")));
        }
        if (request.getParameter("channelName") != null) {
            channel.setChannelName(String.valueOf(request.getParameter("channelName")));
        }
        channel.setType(type);
    }

    private static EmailNotification createEmailNotification(final HttpServletRequest request, NotificationChannelType type) {
        EmailNotification emailNotification = new EmailNotification();
        setCommonParameters(request, emailNotification, type);
        if (request.getParameter("emailAddress") != null) {
            emailNotification.setEmailAddress(String.valueOf(request.getParameter("emailAddress")));
        }
        return emailNotification;
    }
}
