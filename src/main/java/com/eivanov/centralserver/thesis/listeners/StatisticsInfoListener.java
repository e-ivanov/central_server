/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.eivanov.centralserver.thesis.cache.ServerStatus;
import com.eivanov.centralserver.thesis.domain.mongo.SensorReading;
import com.eivanov.centralserver.thesis.dto.ServerReadingMessage;
import com.eivanov.centralserver.thesis.repository.ProcessInfoRepository;
import com.eivanov.centralserver.thesis.repository.SensorReadingRepository;
import com.eivanov.centralserver.thesis.services.NotificationService;
import java.io.IOException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
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
            SensorReading isDuplicate = sensorReadingRepository.findByUUID(message.getUuid());
            if(isDuplicate != null){
                logger.error("Това съобщение вече  е обработено!");
                return;
            }
            notificationInfo = notificationStatisticsCache.getItem(reading.getServerId());
            if (notificationInfo == null) {
                notificationInfo = new NotificationInfo();
                notificationStatisticsCache.updateItem(reading.getServerId(), notificationInfo);
            }
            notificationInfo.setLatestStatisticsReceived(DateTime.now());
            logger.info("Statistics received");
        } catch (IOException ex) {
            logger.error("Възникна грешка! ", ex);
        }

        sensorReadingRepository.save(reading);
        serverStatus.updateItem(reading.getServerId(), reading);

        if (message != null && message.getProcessList() != null) {
            processRepository.saveAll(message.getProcessList());
        }
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
