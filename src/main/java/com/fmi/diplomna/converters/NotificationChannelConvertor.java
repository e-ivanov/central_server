/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.converters;

import com.fmi.diplomna.hibernate.NotificationChannel;
import com.fmi.diplomna.services.NotificationChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author killer
 */
public class NotificationChannelConvertor implements Converter<String, NotificationChannel>{
    
    @Autowired
    private NotificationChannelService notificationChannelService;

    @Override
    public NotificationChannel convert(String s) {
        return notificationChannelService.load(Long.parseLong(s));
    }
    
}
