/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.listeners;

import com.fmi.diplomna.domain.mongo.SensorReading;
import com.fmi.diplomna.dto.ServerReadingMessage;
import org.joda.time.DateTime;

/**
 *
 * @author killer
 */
public class SensorReadingFactory {
    
    public static SensorReading assembleSensorReading(ServerReadingMessage message){
            SensorReading reading = new SensorReading();
            
            reading.setCpuData(message.getCpuData());
            reading.setDiskData(message.getDiskData());
            reading.setMemoryData(message.getMemoryData());
            reading.setNetworkData(message.getNetworkData());
            reading.setServerId(message.getServerId());
            reading.setSystemUptime(message.getSystemUptime());
            reading.setTimestamp(DateTime.now());
            
            return reading;
    }
    
}
