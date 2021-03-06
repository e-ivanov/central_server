/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.repository;

import com.eivanov.centralserver.thesis.domain.mongo.SensorReading;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 *
 * @author killer
 */
public class GenericMongoRepository<T> implements GenericCRUDInterface<T>{
    
    private final Class<T> clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
    @Autowired
    private MongoTemplate mongoTemplate;
    
    
    public T save(T entity) {
        mongoTemplate.save(entity);
        return entity;
    }

    public T load(long id) {
        return mongoTemplate.findById(id, clazz);
    }

    public void delete(T entity) {
        mongoTemplate.remove(entity);
    }
    
    public List<T> loadAll() {
        return mongoTemplate.findAll(clazz);
    }

    public void saveAll(List<T> items) {
        mongoTemplate.insertAll(items);
    }
    
    public MongoTemplate getTemplate(){
        return mongoTemplate;
    }

    @Override
    public void deleteAll(List<T> entities) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
