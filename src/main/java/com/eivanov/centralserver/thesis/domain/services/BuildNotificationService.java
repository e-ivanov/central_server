/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.domain.services;

import com.eivanov.centralserver.thesis.domain.mongo.SensorReading;
import com.eivanov.centralserver.thesis.dto.NotificationChannelType;
import com.eivanov.centralserver.thesis.dto.NotificationDTO;
import com.eivanov.centralserver.thesis.dto.NotificationType;
import com.eivanov.centralserver.thesis.entity.ResourceNotificationPolicy;
import com.eivanov.centralserver.thesis.entity.Server;
import com.eivanov.centralserver.thesis.listeners.NotificationInfo;
import com.eivanov.centralserver.thesis.listeners.NotificationSender;
import com.eivanov.centralserver.thesis.listeners.StatisticsInfoListener;
import com.eivanov.centralserver.thesis.listeners.StatisticsUtils;
import com.eivanov.centralserver.thesis.repository.SensorReadingRepository;
import com.eivanov.centralserver.thesis.services.ServerService;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author killer
 */
@Component
public class BuildNotificationService {

    private static final Logger logger = LoggerFactory.getLogger(BuildNotificationService.class);

    @Autowired
    private ServerService serverService;

    @Autowired
    private SensorReadingRepository sensorReadingRepository;
    
    @Autowired 
    private NotificationSender notificationSender;


    public void processReading(NotificationData info) {
        logger.info("Processing notification Data");

        boolean canSendNotification = true;
        int resendInterval = info.getNotificationConfig().getNotificationInverval();
        DateTime latestNotification = getLatestNotification(info);
        if (latestNotification != null) {
                Seconds diff = Seconds.secondsBetween(latestNotification, DateTime.now());
                logger.info("The diff is :"+info.getType()+diff.toString());
                if (diff.getSeconds() < resendInterval) {
                    canSendNotification = false;
                }
            }
        if (canSendNotification) {
            checkResourceUsage(info);
        }
    }

    private void checkResourceUsage(NotificationData info) {
        DateTime checkInterval = DateTime.now().minusMinutes(info.getNotificationConfig().getNotificationInverval());
        
        List<SensorReading> readings
                = sensorReadingRepository.filterByServerAndDate(info.getReading().getServerId(), checkInterval);
        Server server = serverService.load(info.getReading().getServerId());
        
        NotificationDTO dto = null;
        float avgUsage = StatisticsUtils.calculatePcnt(readings, info);
        if ( avgUsage >= info.getNotificationConfig().getCriticalLevel()) {
            dto = NotificationFactory.buildResourceUsageNotification(server, 
                                      NotificationType.CRITICAL, info,
                                      avgUsage, readings);
        } else if (avgUsage >= info.getNotificationConfig().getWarnLevel()) {
            dto = NotificationFactory.buildResourceUsageNotification(server,
                                           NotificationType.WARN, info,
                                           avgUsage, readings);
        }
        if(dto != null){
            setLatestNotification(info);
            sendNotification(dto);
        }
        setLatestExecutedCheck(info);
    }

    private void sendNotification(NotificationDTO notificationDTO) {
        logger.info(notificationDTO.toString());
        notificationSender.send(notificationDTO);

    }
    
    private DateTime getLatestNotification(NotificationData info){
        DateTime latestNotification = null;
        switch(info.getType()){
            case CPU:
                latestNotification =  info.getnInfo().getLatestCpuNotification();
                break;
            case DISK_USAGE:
                latestNotification =  info.getnInfo().getLastestDiskNotification();
                break;
            case MEMORY:
                latestNotification =  info.getnInfo().getLatestMemoryNotification();
                break;
        }
        return latestNotification;
    }
    

    private void setLatestExecutedCheck(NotificationData info) {
        switch (info.getType()) {
            case CPU:
                info.getnInfo().setLatestExecutedCpuCheck(DateTime.now());
                break;
            case DISK_USAGE:
                info.getnInfo().setLastestExecutedDiskUsageCheck(DateTime.now());
                break;
            case MEMORY:
                info.getnInfo().setLastestExecutedMemoryCheck(DateTime.now());
                break;
        }
    }

    private void setLatestNotification(NotificationData info) {
        switch (info.getType()) {
            case CPU:
                info.getnInfo().setLatestCpuNotification(DateTime.now());
                logger.info("Notification time for CPU is set");
                break;
            case DISK_USAGE:
                info.getnInfo().setLastestDiskNotification(DateTime.now());
                logger.info("Notification time for Disk is set");
                break;
            case MEMORY:
                info.getnInfo().setLatestMemoryNotification(DateTime.now());
                logger.info("Notification time for Memory is set");
                break;
        }
    }
}
