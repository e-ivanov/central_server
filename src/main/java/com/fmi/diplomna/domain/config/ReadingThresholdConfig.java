/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.domain.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author killer
 */
@Document(collection = "reading_threshold_config")
public class ReadingThresholdConfig {
    
    @Id
    private String id;
    private String thresholdTypeId;
    private String serverId;
    private float low_end;
    private float high_end;
    private String sensorTypeId;
    private Map<String, String> notificationGroupLevel = new HashMap<>();
    
    
}
