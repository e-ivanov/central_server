/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.converters;

import com.fmi.diplomna.hibernate.NotificationGroup;
import com.fmi.diplomna.services.NotificationGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author killer
 */
public class NotificationGroupConvertor implements Converter<String, NotificationGroup>{

    @Autowired
    private NotificationGroupService notificationGroupService;
    
    @Override
    public NotificationGroup convert(String s) {
        return notificationGroupService.load(Long.valueOf(s));
    }
    
}
