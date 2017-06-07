/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.services;

import com.eivanov.centralserver.thesis.entity.ResourceNotificationPolicy;
import java.util.List;

/**
 *
 * @author killer
 */
public interface ResourceNotificationPolicyService {
    
    public void save(ResourceNotificationPolicy entity);

    public ResourceNotificationPolicy load(long id);
  
    public void delete(ResourceNotificationPolicy entity);


    public void deleteAll();

    public List<ResourceNotificationPolicy> loadAll();

    public void saveAll(List<ResourceNotificationPolicy> items);
}
