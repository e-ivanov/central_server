/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.listeners;

import com.eivanov.centralserver.thesis.domain.mongo.SensorReading;
import com.eivanov.centralserver.thesis.domain.services.NotificationData;
import com.eivanov.centralserver.thesis.entity.Measure;
import java.util.List;

/**
 *
 * @author killer
 */
public class StatisticsUtils {
    
//    public static float calculateAvgCpuUsage(List<SensorReading> readings){
//        float totalPcnt = 0;
//        for(SensorReading reading : readings){
//            totalPcnt += reading.getCpuData().getUsage();
//        }
//        return calculatePcnt(totalPcnt,readings.size());
//    }
//    
//    public static float calculateAvgDiskIOUsage(List<SensorReading> readings){
//        float totalPcnt = 0;
//        for(SensorReading reading : readings){
//            totalPcnt += reading.getDiskData().getPercOfUsedDisk();
//        }
//        return calculatePcnt(totalPcnt,readings.size());
//    }
//    
//    public static float calculateAvgMemorysage(List<SensorReading> readings){
//        float totalPcnt = 0;
//        for(SensorReading reading : readings){
//            totalPcnt += reading.getMemoryData().getUsagePercent();
//        }
//        return calculatePcnt(totalPcnt,readings.size());
//    }
    
    public static float calculatePcnt(List<SensorReading> readings, NotificationData info){
        float totalPcnt = 0;
        switch(info.getType()){
            case CPU:
                for(SensorReading reading : readings){
                    totalPcnt += reading.getCpuData().getUsage();
                }
                break;
            case DISK_USAGE:
                for(SensorReading reading : readings){
                    totalPcnt += reading.getDiskData().getPercOfUsedDisk();
                }
                break;
            case MEMORY:
                for(SensorReading reading : readings){
                    totalPcnt += reading.getMemoryData().getUsagePercent();
                }
                break;
        }
        return totalPcnt/readings.size();
    }
}
