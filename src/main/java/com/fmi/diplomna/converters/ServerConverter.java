/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.converters;

import com.fmi.diplomna.hibernate.Server;
import com.fmi.diplomna.repository.ServerRepository;
import com.fmi.diplomna.services.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author killer
 */
public class ServerConverter implements Converter<String, Server>{

    @Autowired
    private ServerService serverService;
    
    @Override
    public Server convert(String s) {
        return serverService.load(Long.valueOf(s));
    }
    
}
