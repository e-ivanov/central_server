/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.repository;

import com.eivanov.centralserver.thesis.dto.NotificationChannelType;
import com.eivanov.centralserver.thesis.entity.EmailNotification;
import com.eivanov.centralserver.thesis.entity.NotificationChannel;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationChannelRepositoryImpl extends GenericCRUDRepository<NotificationChannel>implements NotificationChannelRepository {

    private static final Logger logger = LoggerFactory.getLogger(NotificationChannelRepositoryImpl.class);

    @Override
    public List<EmailNotification> getEmailNotifications() {
        return super.getCurrentSession()
                .createQuery("from NotificationChannel where type='EMAIL'").list();
    }

}
