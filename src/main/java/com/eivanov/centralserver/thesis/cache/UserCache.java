/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.cache;

import com.eivanov.centralserver.thesis.entity.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author killer
 */
@Component
public class UserCache extends GenericCache<Long, User>{
    
}
