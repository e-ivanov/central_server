/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.services;

import com.eivanov.centralserver.thesis.entity.NotificationChannel;
import com.eivanov.centralserver.thesis.entity.ResourceNotificationPolicy;
import com.eivanov.centralserver.thesis.entity.Server;
import com.eivanov.centralserver.thesis.entity.User;
import com.eivanov.centralserver.thesis.repository.ResourceNotificationPolicyRepository;
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
    
    @Autowired NotificationChannelService notificationChannelService;
    
    @Override
    public void save(ResourceNotificationPolicy entity) {
        if(entity.getId() != null){
            ResourceNotificationPolicy rsDB = notificationPolicyRepository.load(entity.getId());
            rsDB.setCpuResourceConfig(entity.getCpuResourceConfig());
            rsDB.setMemoryResourceConfig(entity.getMemoryResourceConfig());
            rsDB.setDiskUsageResourceConfig(entity.getDiskUsageResourceConfig());
            rsDB.setName(entity.getName());
            rsDB.setServerUnresponsiveInterval(entity.getServerUnresponsiveInterval());
            if(entity.getServerSet() != null){
                rsDB.setServerSet(entity.getServerSet());
            }
            if(entity.getNotificationChannelSet() != null){
                rsDB.setNotificationChannelSet(entity.getNotificationChannelSet());
            }
            updateServers(entity, rsDB);
            updateNotificationChannels(entity, rsDB);
            notificationPolicyRepository.save(rsDB);
        }else{
            ResourceNotificationPolicy finalPolicy = (ResourceNotificationPolicy)notificationPolicyRepository.save(entity);
            for(Server server : finalPolicy.getServerSet()){
                server.setNotificationPolicy(finalPolicy);
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
            }
            for (Server server : newServers) {
                server.setNotificationPolicy(oldEntity);
                serverService.save(server);
            }
    }
    
    private void updateNotificationChannels(ResourceNotificationPolicy newEntity, ResourceNotificationPolicy oldEntity){
        Set<NotificationChannel> removedChannels = Sets.difference(oldEntity.getNotificationChannelSet(), newEntity.getNotificationChannelSet());
            Set<NotificationChannel> newChannels = Sets.difference(newEntity.getNotificationChannelSet(), oldEntity.getNotificationChannelSet());
            for (NotificationChannel nchannel : removedChannels) {
                nchannel.getResourceNotificationPolicySet().remove(oldEntity);
                notificationChannelService.save(nchannel);
            }
            for (NotificationChannel nchannel : newChannels) {
                nchannel.getResourceNotificationPolicySet().add(oldEntity);
                notificationChannelService.save(nchannel);
            }
    }
}
