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
import java.util.List;
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
        int resendInterval = 25;
        DateTime latestNotification = getLatestNotification(info);
        if (latestNotification != null) {
                Seconds diff = Seconds.secondsBetween(latestNotification, DateTime.now());
                logger.info("The diff is :"+diff.toString());
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
        if (StatisticsUtils.calculatePcnt(readings, info) >= info.getNotificationConfig().getCriticalLevel()) {
            dto = NotificationFactory.buildResourceUsageNotification(server, NotificationType.CRITICAL, info.getType());
        } else if (StatisticsUtils.calculatePcnt(readings, info) >= info.getNotificationConfig().getWarnLevel()) {
            dto = NotificationFactory.buildResourceUsageNotification(server, NotificationType.WARN, info.getType());
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
