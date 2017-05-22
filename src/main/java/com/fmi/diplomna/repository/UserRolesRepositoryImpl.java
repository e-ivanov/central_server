/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.repository;

import com.fmi.diplomna.hibernate.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository
public class UserRolesRepositoryImpl extends GenericCRUDRepository<UserRole> implements UserRolesRepository{
    private static final Logger logger = LoggerFactory.getLogger(UserRolesRepositoryImpl.class);
}
