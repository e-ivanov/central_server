/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.converters;

import com.eivanov.centralserver.thesis.entity.UserRole;
import com.eivanov.centralserver.thesis.services.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author killer
 */
public class UserRoleConvertor implements Converter<String, UserRole>{
    
    @Autowired
    private UserRolesService userRolesService;

    @Override
    public UserRole convert(String s) {
        return userRolesService.loadUserRole(s);
    }
    
}
