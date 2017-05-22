/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fmi.diplomna.cache.ServerStatus;
import com.fmi.diplomna.domain.mongo.SensorReading;
import com.fmi.diplomna.dto.NotificationDTO;
import com.fmi.diplomna.dto.NotificationChannel;
import com.fmi.diplomna.dto.ServerReadingMessage;
import com.fmi.diplomna.hibernate.ResourceNotificationPolicy;
import com.fmi.diplomna.repository.ProcessInfoRepository;
import com.fmi.diplomna.repository.SensorReadingRepository;
import com.fmi.diplomna.repository.ServerRepository;
import com.fmi.diplomna.services.NotificationService;
import com.fmi.diplomna.services.ServerService;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.Seconds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.stereotype.Component;

/**
 *
 * @author killer
 */
@Component
public class StatisticsInfoListener implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsInfoListener.class);

    @Autowired
    private SensorReadingRepository sensorReadingRepository;

    @Autowired
    private ProcessInfoRepository processRepository;

    @Autowired
    private ServerService serverService;

    @Autowired
    private ObjectMapper jsonMessageConvertor;

    @Autowired
    private ServerStatus serverStatus;

    @Autowired
    private NotificationStatisticsCache notificationStatisticsCache;
    
    @Autowired
    private NotificationService notificationService;

    @Override
    public void onMessage(Message msg) {
        logger.info(new String(msg.getBody()));
        ServerReadingMessage message = null;
        SensorReading reading = null;
        NotificationInfo notificationInfo = null;
        try {
            message = jsonMessageConvertor.readValue(new String(msg.getBody()), ServerReadingMessage.class);
            reading = SensorReadingFactory.assembleSensorReading(message);
            notificationInfo = notificationStatisticsCache.getItem(reading.getServerId());
            if (notificationInfo == null) {
                notificationInfo = new NotificationInfo();
                notificationStatisticsCache.updateItem(reading.getServerId(), notificationInfo);
            }
            notificationInfo.setLatestStatisticsReceived(DateTime.now());
            logger.info("Statistics received");
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }

        if (reading != null) {
            sensorReadingRepository.save(reading);
            serverStatus.updateItem(reading.getServerId(), reading);
        }

//        if (message != null && message.getProcessList() != null) {
//            processRepository.saveAll(message.getProcessList());
//        }
        checkResourceUsage(reading, notificationInfo);

    }

    private void checkResourceUsage(SensorReading reading, NotificationInfo info) {

        if (info.getLatestExecutedCpuCheck() == null) {
            info.setLatestExecutedCpuCheck(DateTime.now());
            info.setLastestExecutedMemoryCheck(DateTime.now());
            info.setLastestExecutedDiskUsageCheck(DateTime.now());
            logger.info("LAST EXECUTED CHECK WAS NULL");
            return;
        }
        notificationService.checkNotification(reading, info);

    }

}
