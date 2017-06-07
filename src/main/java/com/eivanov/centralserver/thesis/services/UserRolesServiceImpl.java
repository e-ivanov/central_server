/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.services;

import com.eivanov.centralserver.thesis.entity.UserRole;
import com.eivanov.centralserver.thesis.repository.UserRolesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class UserRolesServiceImpl implements UserRolesService {
    
    @Autowired
    private UserRolesRepository userRolesRepository;

    @Override
    public List<UserRole> getUserRoles() {
        return userRolesRepository.loadAll();
    }

    @Override
    public void delete(UserRole role) {
        userRolesRepository.delete(role);
    }

    @Override
    public void save(UserRole role) {
        userRolesRepository.save(role);
    }

    @Override
    public UserRole loadUserRole(String id) {
        return userRolesRepository.load(Long.parseLong(id));
    }
}
