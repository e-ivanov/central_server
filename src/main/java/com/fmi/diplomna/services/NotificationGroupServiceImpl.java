/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.services;

import com.fmi.diplomna.hibernate.NotificationGroup;
import com.fmi.diplomna.hibernate.Server;
import com.fmi.diplomna.hibernate.User;
import com.fmi.diplomna.repository.NotificationGroupRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotificationGroupServiceImpl implements NotificationGroupService {

    @Autowired
    private NotificationGroupRepository repository;
    
    @Autowired
    private ServerService serverService;
    
    @Autowired
    private UserService userService;
    
    @Override
    public void delete(NotificationGroup entity) {
        for(Server server : entity.getServerSet()){
            server.getNotificationGroupSet().remove(entity);
            serverService.save(server);
        }
        for(User user : entity.getUserSet()){
            user.getNotificationGroupSet().remove(entity);
        }
        repository.delete(entity);
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NotificationGroup load(long id) {
        return repository.load(id);
    }

    @Override
    public List<NotificationGroup> loadAll() {
        return repository.loadAll();
    }

    @Override
    public void save(NotificationGroup entity) {
        repository.save(entity);
    }

    @Override
    public void saveAll(List<NotificationGroup> items) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
