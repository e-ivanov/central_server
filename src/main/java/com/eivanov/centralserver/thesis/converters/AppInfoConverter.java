/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.converters;

import com.eivanov.centralserver.thesis.entity.AppInfo;
import com.eivanov.centralserver.thesis.services.AppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author killer
 */
public class AppInfoConverter implements Converter<String, AppInfo>{

    @Autowired
    private AppInfoService appInfoService;
    
    @Override
    public AppInfo convert(String s) {
        return appInfoService.load(Long.valueOf(s));
    }
    
}
