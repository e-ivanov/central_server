/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.services;

import com.eivanov.centralserver.thesis.domain.mongo.SensorReading;
import com.eivanov.centralserver.thesis.listeners.NotificationInfo;

/**
 *
 * @author killer
 */
public interface NotificationService {
    void checkNotification(SensorReading reading, NotificationInfo info);
}
