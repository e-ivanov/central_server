/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.repository;

import com.eivanov.centralserver.thesis.entity.EmailNotification;
import com.eivanov.centralserver.thesis.entity.NotificationChannel;
import java.util.List;


/**
 *
 * @author killer
 */
public interface NotificationChannelRepository extends GenericCRUDInterface<NotificationChannel>{
    
    public List<EmailNotification> getEmailNotifications();
}
