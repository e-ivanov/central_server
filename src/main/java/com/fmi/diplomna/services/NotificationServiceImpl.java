/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.services;

import com.fmi.diplomna.domain.mongo.SensorReading;
import com.fmi.diplomna.domain.services.BuildNotificationService;
import com.fmi.diplomna.domain.services.CheckType;
import com.fmi.diplomna.domain.services.NotificationData;
import com.fmi.diplomna.hibernate.ResourceNotificationPolicy;
import com.fmi.diplomna.hibernate.Server;
import com.fmi.diplomna.listeners.NotificationInfo;
import com.fmi.diplomna.listeners.NotificationStatisticsCache;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationStatisticsCache notificationStatisticsCache;

    @Autowired
    private BuildNotificationService buildNotificationService;

    @Autowired
    private ServerService serverService;

    @Override
    public void checkNotification(SensorReading reading, NotificationInfo info) {
        
        Server server = serverService.load(reading.getServerId());
        if(server == null)return;
        ResourceNotificationPolicy policy = server.getNotificationPolicy();
        if(policy == null)return;

        DateTime currDate = DateTime.now();

        Seconds cpuDiff = Seconds.secondsBetween(info.getLatestExecutedCpuCheck(), currDate);
        Seconds diskDiff = Seconds.secondsBetween(info.getLastestExecutedDiskUsageCheck(), currDate);
        Seconds memoryDiff = Seconds.secondsBetween(info.getLastestExecutedMemoryCheck(), currDate);
        
        buildNotificationService.processReading(
                new NotificationData(cpuDiff.getSeconds(), policy.getCpuResourceConfig(), info.getLatestCpuNotification(), CheckType.CPU, reading, info));
        buildNotificationService.processReading(
                new NotificationData(diskDiff.getSeconds(), policy.getDiskUsageResourceConfig(), info.getLastestDiskNotification(), CheckType.DISK_USAGE, reading, info));
        buildNotificationService.processReading(
                new NotificationData(memoryDiff.getSeconds(), policy.getMemoryResourceConfig(), info.getLatestMemoryNotification(), CheckType.MEMORY, reading, info));


    }
}
