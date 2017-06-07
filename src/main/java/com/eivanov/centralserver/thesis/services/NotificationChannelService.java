/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.services;

import com.eivanov.centralserver.thesis.entity.EmailNotification;
import com.eivanov.centralserver.thesis.entity.NotificationChannel;
import java.util.List;

/**
 *
 * @author killer
 */
public interface NotificationChannelService {
    void delete(NotificationChannel entity);

    void deleteAll();

    NotificationChannel load(long id);

    List<NotificationChannel> loadAll();

    void save(NotificationChannel entity);

    void saveAll(List<NotificationChannel> items);
    
    public List<EmailNotification> getEmailNotifications();
}
