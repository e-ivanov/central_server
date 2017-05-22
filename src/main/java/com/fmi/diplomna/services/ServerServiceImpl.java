/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.services;

import com.fmi.diplomna.hibernate.NotificationGroup;
import com.fmi.diplomna.hibernate.ResourceNotificationPolicy;
import com.fmi.diplomna.hibernate.Server;
import com.fmi.diplomna.hibernate.User;
import com.fmi.diplomna.repository.ServerRepository;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author killer
 */
@Service
@Transactional
public class ServerServiceImpl implements ServerService {

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private NotificationGroupService notificationGroupService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ResourceNotificationPolicyService resourceNotificationPolicyService;

    @Override
    public void save(Server entity) {
        if (entity.getId() != null) {
            Server serverFromDB = serverRepository.load(entity.getId());
            serverFromDB.setDomain(entity.getDomain());
            serverFromDB.setIpAddress(entity.getIpAddress());
            serverFromDB.setName(entity.getName());
            serverFromDB.setNotificationPolicy(entity.getNotificationPolicy());
            serverFromDB.setActive(entity.isActive());
            updateNotificationGroups(entity, serverFromDB);
            updateUsers(entity, serverFromDB);
            serverRepository.save(serverFromDB);
        } else {
            
            for (NotificationGroup group : entity.getNotificationGroupSet()) {
                group.getServerSet().add(entity);
                notificationGroupService.save(group);
            }
            serverRepository.save(entity);
        }

    }

    private void updateNotificationGroups(Server newEntity, Server oldEntity) {
        Set<NotificationGroup> removedGroups = Sets.difference(oldEntity.getNotificationGroupSet(), newEntity.getNotificationGroupSet());
        Set<NotificationGroup> newGroups = Sets.difference(newEntity.getNotificationGroupSet(), oldEntity.getNotificationGroupSet());
        for (NotificationGroup group : removedGroups) {
            group.getServerSet().remove(oldEntity);
            notificationGroupService.save(group);
            oldEntity.getNotificationGroupSet().remove(group);
        }
        for (NotificationGroup group : newGroups) {
            group.getServerSet().add(oldEntity);
            notificationGroupService.save(group);
            oldEntity.getNotificationGroupSet().add(group);
        }
    }
    private void updateUsers(Server newEntity, Server oldEntity) {
        Set<User> removedUsers = Sets.difference(oldEntity.getUserSet(), newEntity.getUserSet());
        Set<User> newUsers = Sets.difference(newEntity.getUserSet(), oldEntity.getUserSet());
        for (User user : removedUsers) {
            user.getServerSet().remove(oldEntity);
            oldEntity.getUserSet().remove(user);
        }
        for (User user : newUsers) {
            user.getServerSet().add(oldEntity);
            oldEntity.getUserSet().add(user);
        }
    }

    @Override
    public Server load(long id) {
        return serverRepository.load(id);
    }

    @Override
    public void delete(Server entity) {
        for(User user : entity.getUserSet()){
            user.getServerSet().remove(entity);
            userService.save(user);
        }
        if(entity.getNotificationPolicy() != null){
            ResourceNotificationPolicy policy = entity.getNotificationPolicy();
            policy.getServerSet().remove(entity);
            resourceNotificationPolicyService.save(policy);
        }
        for(NotificationGroup ngroup: entity.getNotificationGroupSet()){
            ngroup.getServerSet().remove(entity);
            notificationGroupService.save(ngroup);
        }
        serverRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Server> loadAll() {
        return serverRepository.loadAll();
    }

    @Override
    public void saveAll(List<Server> items) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
