/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.services;

import com.fmi.diplomna.hibernate.NotificationChannel;
import com.fmi.diplomna.hibernate.ResourceNotificationPolicy;
import com.fmi.diplomna.hibernate.Server;
import com.fmi.diplomna.hibernate.User;
import com.fmi.diplomna.repository.ResourceNotificationPolicyRepository;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResourceNotificationPolicyServiceImpl implements ResourceNotificationPolicyService {

    @Autowired
    private ResourceNotificationPolicyRepository notificationPolicyRepository;
    
    @Autowired
    private ServerService serverService;
    
    @Override
    public void save(ResourceNotificationPolicy entity) {
        if(entity.getId() != null){
            ResourceNotificationPolicy rsDB = notificationPolicyRepository.load(entity.getId());
            rsDB.setCpuResourceConfig(entity.getCpuResourceConfig());
            rsDB.setMemoryResourceConfig(entity.getMemoryResourceConfig());
            rsDB.setDiskUsageResourceConfig(entity.getDiskUsageResourceConfig());
            rsDB.setName(entity.getName());
            rsDB.setServerUnresponsiveInterval(entity.getServerUnresponsiveInterval());
            updateServers(entity, rsDB);
            notificationPolicyRepository.save(rsDB);
        }else{
            notificationPolicyRepository.save(entity);
            for(Server server : entity.getServerSet()){
                server.setNotificationPolicy(entity);
                serverService.save(server);
            }
        }
    }

    @Override
    public ResourceNotificationPolicy load(long id) {
        return notificationPolicyRepository.load(id);
    }

    @Override
    public void delete(ResourceNotificationPolicy entity) {
        for(Server server : entity.getServerSet()){
            server.setNotificationPolicy(null);
            serverService.save(server);
        }

        notificationPolicyRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ResourceNotificationPolicy> loadAll() {
        return notificationPolicyRepository.loadAll();
    }

    @Override
    public void saveAll(List<ResourceNotificationPolicy> items) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void updateServers(ResourceNotificationPolicy newEntity, ResourceNotificationPolicy oldEntity){
        Set<Server> removedServers = Sets.difference(oldEntity.getServerSet(), newEntity.getServerSet());
            Set<Server> newServers = Sets.difference(newEntity.getServerSet(), oldEntity.getServerSet());
            for (Server server : removedServers) {
                server.setNotificationPolicy(null);
                serverService.save(server);
                oldEntity.getServerSet().remove(server);
            }
            for (Server server : newServers) {
                server.setNotificationPolicy(oldEntity);
                serverService.save(server);
                oldEntity.getServerSet().add(server);
            }
    }
}
