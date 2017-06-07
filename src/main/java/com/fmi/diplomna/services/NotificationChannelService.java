/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.services;

import com.fmi.diplomna.hibernate.EmailNotification;
import com.fmi.diplomna.hibernate.NotificationChannel;
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
