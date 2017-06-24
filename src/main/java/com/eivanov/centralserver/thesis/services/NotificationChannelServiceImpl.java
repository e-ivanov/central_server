/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.services;

import com.eivanov.centralserver.thesis.entity.AppInfo;
import com.eivanov.centralserver.thesis.entity.EmailNotification;
import com.eivanov.centralserver.thesis.entity.NotificationChannel;
import com.eivanov.centralserver.thesis.entity.ResourceNotificationPolicy;
import com.eivanov.centralserver.thesis.repository.AppInfoRepository;
import com.eivanov.centralserver.thesis.repository.NotificationChannelRepository;
import com.eivanov.centralserver.thesis.repository.ResourceNotificationPolicyRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class NotificationChannelServiceImpl implements NotificationChannelService {
    
    
    @Autowired
    private AppInfoService appInfoService;
    
    @Autowired
    private ResourceNotificationPolicyService resourceNotificationPolicyService;
    
    @Autowired
    private NotificationChannelRepository repository;

    @Override
    public void delete(NotificationChannel entity) {
        for(ResourceNotificationPolicy policy : entity.getResourceNotificationPolicySet()){
            policy.getNotificationChannelSet().remove(entity);
            resourceNotificationPolicyService.save(policy);
        }
        
        for(AppInfo appInfo : entity.getAppInfoSet()){
            appInfo.getNotificationChannelSet().remove(entity);
            appInfoService.save(appInfo);
        }
       
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
