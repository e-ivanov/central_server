/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.converters;

import com.eivanov.centralserver.thesis.entity.Server;
import com.eivanov.centralserver.thesis.repository.ServerRepository;
import com.eivanov.centralserver.thesis.services.ServerService;
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
