/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.converters;

import com.fmi.diplomna.hibernate.User;
import com.fmi.diplomna.repository.UserRepository;
import com.fmi.diplomna.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author killer
 */
public class UserConverter implements Converter<String, User>{

    @Autowired
    private UserService userService;
    
    @Override
    public User convert(String s) {
        return userService.load(Long.valueOf(s));
    }
    
}
