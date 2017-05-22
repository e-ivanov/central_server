/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.domain.config;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author killer
 */
@Document(collection = "sensor_reading")
public class SensorReading {
    
    @Id
    private String id;
    private String sensorTypeId;
    private String serverId;
    private float value;
    private LocalDateTime moment;

    public SensorReading(String sensorTypeId, String serverId, float value, LocalDateTime moment) {
        this.sensorTypeId = sensorTypeId;
        this.serverId = serverId;
        this.value = value;
        this.moment = moment;
    }

    public String getId() {
        return id;
    }

    public String getSensorTypeId() {
        return sensorTypeId;
    }

    public void setSensorTypeId(String sensorTypeId) {
        this.sensorTypeId = sensorTypeId;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public LocalDateTime getMoment() {
        return moment;
    }

    public void setMoment(LocalDateTime moment) {
        this.moment = moment;
    }
    
    
    
}
