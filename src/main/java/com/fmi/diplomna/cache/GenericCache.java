/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

/**
 *
 * @author killer
 */


public class GenericCache<PK, T> {
    
    private final Map<PK, T> cache = new ConcurrentHashMap<PK, T>();
    
    public T getItem(PK key){
        return cache.get(key);
    }
    public void updateItem(PK key, T newValue){
        cache.put(key, newValue);
    }
    public void evictItem(PK key){
        cache.remove(key);
    }
    
    public List<T> getAllValues(){
        return new ArrayList<T>(cache.values());
    }
    
    public long getCacheSize(){
        return cache.size();
    }
}
