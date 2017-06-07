/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.repository;

import com.eivanov.centralserver.thesis.domain.mongo.SensorReading;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author killer
 */
public interface SensorReadingRepository extends GenericCRUDInterface<SensorReading>{
     public List<SensorReading> findAllReadings();
     public List<SensorReading> filterByServerAndDate(long serverId, DateTime date);
     public SensorReading findByUUID(String uuid);
}
