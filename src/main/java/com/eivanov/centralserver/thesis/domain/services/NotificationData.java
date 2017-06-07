/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.domain.services;

import com.eivanov.centralserver.thesis.domain.mongo.SensorReading;
import com.eivanov.centralserver.thesis.entity.Measure;
import com.eivanov.centralserver.thesis.listeners.NotificationInfo;
import org.joda.time.DateTime;

/**
 *
 * @author killer
 */
public class NotificationData {
    private final int diffInSeconds;
    private final Measure notificationConfig;
    private final DateTime latestNotification;
    private final CheckType type;
    private final SensorReading reading;
    private final NotificationInfo nInfo;

    public NotificationData(int diffInSeconds, Measure config,
                             DateTime latestNotification, CheckType type,
                             SensorReading reading, NotificationInfo info) {
        this.diffInSeconds = diffInSeconds;
        this.notificationConfig = config;
        this.latestNotification = latestNotification;
        this.type = type;
        this.reading = reading;
        this.nInfo = info;
    }

    public int getDiffInSeconds() {
        return diffInSeconds;
    }

    public Measure getNotificationConfig() {
        return notificationConfig;
    }

    public DateTime getLatestNotification() {
        return latestNotification;
    }

    public CheckType getType() {
        return type;
    }

    public SensorReading getReading() {
        return reading;
    }

    public NotificationInfo getnInfo() {
        return nInfo;
    }
    
}
