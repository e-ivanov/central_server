/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.repository;

import com.fmi.diplomna.domain.mongo.SensorReading;
import java.util.List;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SensorReadingRepositoryImpl extends GenericMongoRepository<SensorReading> implements SensorReadingRepository {

    private static final Logger logger = LoggerFactory.getLogger(SensorReadingRepositoryImpl.class);
    
    @Override
    public List<SensorReading> findAllReadings() {
        return getTemplate().findAll(SensorReading.class, "sensor_reading");
    }

    @Override
    public List<SensorReading> filterByServerAndDate(long serverId, DateTime date) {
        Query query = new Query();
        query.addCriteria(Criteria.where("serverId").is(serverId));
        query.addCriteria(Criteria.where("timestamp").gte(date));
        return getTemplate().find(query, SensorReading.class, "sensor_reading");
    }
    
 
    
}
