/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.services;

import com.eivanov.centralserver.thesis.entity.User;
import java.util.List;

/**
 *
 * @author killer
 */
public interface UserService {
    
    void delete(User entity);

    void deleteAll();

    User load(long id);

    List<User> loadAll();

    void save(User entity);

    void saveAll(List<User> items);
    
    User loadByUserName(String userName);
}
