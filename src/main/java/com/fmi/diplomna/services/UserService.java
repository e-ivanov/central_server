/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.services;

import com.fmi.diplomna.hibernate.User;
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
