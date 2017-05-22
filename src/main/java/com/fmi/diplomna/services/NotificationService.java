/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.services;

import com.fmi.diplomna.domain.mongo.SensorReading;
import com.fmi.diplomna.listeners.NotificationInfo;

/**
 *
 * @author killer
 */
public interface NotificationService {
    void checkNotification(SensorReading reading, NotificationInfo info);
}
