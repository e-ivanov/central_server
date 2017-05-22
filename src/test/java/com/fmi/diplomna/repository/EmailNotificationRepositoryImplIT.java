/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.repository;

import com.fmi.diplomna.config.MainConfig;
import com.fmi.diplomna.config.SecurityConfig;
import com.fmi.diplomna.config.ServiceConfig;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
