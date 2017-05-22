/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.services;

import com.fmi.diplomna.hibernate.EmailNotification;
import com.fmi.diplomna.repository.EmailNotificationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fmi.diplomna.repository.NotificationRepository;

@Service
@Transactional
public class EmailNotificationServiceImpl implements EmailNotificationService {

    @Autowired
    private EmailNotificationRepository emailNotificationRepository;
    
    @Override
    public void delete(EmailNotification entity) {
        emailNotificationRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmailNotification load(long id) {
        return emailNotificationRepository.load(id);
    }

    @Override
    public List<EmailNotification> loadAll() {
        return emailNotificationRepository.loadAll();
    }

    @Override
    public void save(EmailNotification entity) {
        emailNotificationRepository.save(entity);
    }

    @Override
    public void saveAll(List<EmailNotification> items) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
