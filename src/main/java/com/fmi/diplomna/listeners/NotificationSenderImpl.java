/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.listeners;

import com.fmi.diplomna.dto.NotificationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationSenderImpl implements NotificationSender {

    private static final Logger logger = LoggerFactory.getLogger(NotificationSenderImpl.class);
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Override
    public void send(NotificationDTO notification) {
        rabbitTemplate.convertAndSend(notification);
        logger.info(notification.toString());
    }
    
}
