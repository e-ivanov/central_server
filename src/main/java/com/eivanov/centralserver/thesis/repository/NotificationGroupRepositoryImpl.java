/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.repository;

import com.eivanov.centralserver.thesis.entity.NotificationGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationGroupRepositoryImpl extends GenericCRUDRepository<NotificationGroup> implements NotificationGroupRepository {

    private static final Logger logger = LoggerFactory.getLogger(NotificationChannelRepositoryImpl.class);

}
