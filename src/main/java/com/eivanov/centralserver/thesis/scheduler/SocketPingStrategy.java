/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author killer
 */
@Component
@Scope("prototype")
public class SocketPingStrategy implements PingStrategy{

    private static final Logger logger = LoggerFactory.getLogger(SocketPingStrategy.class);
    
    @Override
    public boolean executePing() {
        logger.info("The strategy was called");
        return true;
    }
    
}
