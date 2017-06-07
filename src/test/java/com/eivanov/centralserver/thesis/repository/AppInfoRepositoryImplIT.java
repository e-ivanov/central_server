/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.repository;

import com.eivanov.centralserver.thesis.repository.AppInfoRepository;
import com.eivanov.centralserver.thesis.config.MainConfig;
import com.eivanov.centralserver.thesis.config.SecurityConfig;
import com.eivanov.centralserver.thesis.config.ServiceConfig;
import com.eivanov.centralserver.thesis.entity.AppInfo;
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
public class AppInfoRepositoryImplIT {
    
    @Autowired
    private AppInfoRepository appInfoRepository;

    
    public AppInfoRepositoryImplIT() {
    }

    /**
     * Test of save method, of class AppInfoRepositoryImpl.
     */
    @Test
    public void testSave() {
        AppInfo info = new AppInfo();
        info.setHearthbeatInterval(20);
        info.setName("test");
        info.setPath("shomepath");
        info.setPort("5368");
        info.setProtocol("https:");
        info.setUnresponsiveInterval(25);
        info.setUrl("test.com");
        
        appInfoRepository.save(info);
        long id = info.getId();
        
        AppInfo persistentInfo = appInfoRepository.load(id);
        assertNotNull(persistentInfo);
        assertEquals(info.getHearthbeatInterval(), persistentInfo.getHearthbeatInterval());
        

    }

    /**
     * Test of load method, of class AppInfoRepositoryImpl.
     */
    @Test
    public void testLoad() {
    }
    
    @Test(expected = NullPointerException.class)
    public void loadNonExistentInfo(){
        AppInfo info = appInfoRepository.load(69);
        info.getId();

    }

    /**
     * Test of delete method, of class AppInfoRepositoryImpl.
     */
    @Test
    public void testDelete() {
    }

    /**
     * Test of deleteAll method, of class AppInfoRepositoryImpl.
     */
    @Test
    public void testDeleteAll() {
    }

    /**
     * Test of loadAll method, of class AppInfoRepositoryImpl.
     */
    @Test
    public void testLoadAll() {
    }

    /**
     * Test of saveAll method, of class AppInfoRepositoryImpl.
     */
    @Test
    public void testSaveAll() {
    }
    
}
