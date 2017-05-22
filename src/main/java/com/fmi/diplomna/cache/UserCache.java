/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.cache;

import com.fmi.diplomna.hibernate.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author killer
 */
@Component
public class UserCache extends GenericCache<Long, User>{
    
}
