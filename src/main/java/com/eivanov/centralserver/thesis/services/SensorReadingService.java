/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.services;

import com.eivanov.centralserver.thesis.domain.mongo.SensorReading;
import java.util.List;

/**
 *
 * @author killer
 */
public interface SensorReadingService{
    
    public List<SensorReading> getAllReadings();
}
