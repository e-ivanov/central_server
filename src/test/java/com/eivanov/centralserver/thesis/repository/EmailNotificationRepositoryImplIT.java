/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.repository;

import com.eivanov.centralserver.thesis.config.MainConfig;
import com.eivanov.centralserver.thesis.config.SecurityConfig;
import com.eivanov.centralserver.thesis.config.ServiceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author killer
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SecurityConfig.class,ServiceConfig.class, MainConfig.class})
@WebAppConfiguration
@Transactional
public class EmailNotificationRepositoryImplIT {

    public EmailNotificationRepositoryImplIT() {
    }

    /**
     * Test of save method, of class EmailNotificationRepositoryImpl.
     */
    @Test
    public void testSave() {
        
    }

    /**
     * Test of load method, of class EmailNotificationRepositoryImpl.
     */
    @Test
    public void testLoad() {
    }

    /**
     * Test of delete method, of class EmailNotificationRepositoryImpl.
     */
    @Test
    public void testDelete() {
    }

    /**
     * Test of deleteAll method, of class EmailNotificationRepositoryImpl.
     */
    @Test
    public void testDeleteAll() {
    }

    /**
     * Test of loadAll method, of class EmailNotificationRepositoryImpl.
     */
    @Test
    public void testLoadAll() {
    }

    /**
     * Test of saveAll method, of class EmailNotificationRepositoryImpl.
     */
    @Test
    public void testSaveAll() {
    }
    
}
