/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.services;

import com.fmi.diplomna.hibernate.EmailNotification;
import com.fmi.diplomna.hibernate.NotificationChannel;
import com.fmi.diplomna.repository.NotificationChannelRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class NotificationChannelServiceImpl implements NotificationChannelService {
    
    @Autowired
    private NotificationChannelRepository repository;

    @Override
    public void delete(NotificationChannel entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NotificationChannel load(long id) {
        return repository.load(id);
    }

    @Override
    public List<NotificationChannel> loadAll() {
        return repository.loadAll();
    }

    @Override
    public void save(NotificationChannel entity) {
        repository.save(entity);
    }

    @Override
    public void saveAll(List<NotificationChannel> items) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EmailNotification> getEmailNotifications() {
        return repository.getEmailNotifications();
    }
    
}
