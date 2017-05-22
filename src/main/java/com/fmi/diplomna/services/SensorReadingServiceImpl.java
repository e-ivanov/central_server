/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.services;

import com.fmi.diplomna.domain.mongo.SensorReading;
import com.fmi.diplomna.repository.SensorReadingRepository;
import java.util.List;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorReadingServiceImpl implements SensorReadingService {
    
    @Autowired
    private SensorReadingRepository sensorReadingRepository;
    
    @Autowired
    private RabbitTemplate template;

    @Override
    public List<SensorReading> getAllReadings() {
        return sensorReadingRepository.findAllReadings();
    }
    
   
    
}
