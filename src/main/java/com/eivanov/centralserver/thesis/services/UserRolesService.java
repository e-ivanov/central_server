/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.services;

import com.eivanov.centralserver.thesis.entity.UserRole;
import java.util.List;

/**
 *
 * @author killer
 */
public interface UserRolesService {
    public List<UserRole> getUserRoles();
    public void delete(UserRole role);
    public void save(UserRole role);
    public UserRole loadUserRole(String id);
}
