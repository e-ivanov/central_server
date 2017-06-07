/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.converters;

import com.eivanov.centralserver.thesis.entity.ResourceNotificationPolicy;
import com.eivanov.centralserver.thesis.repository.ResourceNotificationPolicyRepository;
import com.eivanov.centralserver.thesis.services.ResourceNotificationPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author killer
 */
public class ResourcePolicyConverter implements Converter<String, ResourceNotificationPolicy>{

    @Autowired
    private ResourceNotificationPolicyService notificationPolicyServer;
    
    @Override
    public ResourceNotificationPolicy convert(String s) {
        return notificationPolicyServer.load(Long.valueOf(s));
    }
    
}
