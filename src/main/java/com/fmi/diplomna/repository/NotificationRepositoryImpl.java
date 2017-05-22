/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.repository;

import com.fmi.diplomna.dto.NotificationChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepositoryImpl extends GenericCRUDRepository<NotificationChannel>implements NotificationRepository {

    private static final Logger logger = LoggerFactory.getLogger(NotificationRepositoryImpl.class);

}
