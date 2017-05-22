/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.repository;

import com.fmi.diplomna.hibernate.User;

/**
 *
 * @author killer
 */
public interface UserRepository extends GenericCRUDInterface<User>{
    public User loadByUserName(String userName);
}
