/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.web.controllers;

import com.fmi.diplomna.config.MainConfig;
import com.fmi.diplomna.config.MongoConfig;
import com.fmi.diplomna.config.SecurityConfig;
import com.fmi.diplomna.config.ServiceConfig;
import com.fmi.diplomna.hibernate.AppInfo;
import com.fmi.diplomna.services.AppInfoService;
import com.fmi.diplomna.services.NotificationChannelService;
import com.fmi.diplomna.services.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.springframework.ui.ModelMap;

/**
 *
 * @author killer
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SecurityConfig.class, ServiceConfig.class, MainConfig.class, MongoConfig.class})
@WithMockUser(username="test@example.com",roles={"ADMIN"})
@WebAppConfiguration
@java.lang.SuppressWarnings("squid:S1313")
public class AppInfoControllerTest {

    @Autowired
    private WebApplicationContext wac;
    
    private MockMvc mockMvc;
    
    @Autowired
    private AppInfoService appInfoService;

    @Mock
    private UserService userService;

    @Mock
    private NotificationChannelService notificationChannelService;
    
    @Spy
    private List<AppInfo> infos = new ArrayList<>();
    
    @Mock
    private ModelMap model;

    @InjectMocks
    private AppInfoController appInfoController;

    public AppInfoControllerTest() {
        MockitoAnnotations.initMocks(this);
        infos = stubList();
        
    }

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    /**
     * Test of getUserList method, of class AppInfoController.
     * @throws java.lang.Exception
     */
    @Test
    
    public void testGetUserList() {
        List<AppInfo> infosFromDb = appInfoService.loadAll();
        try {
            this.mockMvc.perform(get("/appinfo/list")).andDo(MockMvcResultHandlers.print())
                    .andExpect(view().name("appinfo/list"))
                    .andExpect(model().attribute("appinfolist", CoreMatchers.equalTo(infosFromDb)));
        } catch (Exception ex) {
            Logger.getLogger(AppInfoControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    /**
     * Test of getNotifications method, of class AppInfoController.
     */
    @Test
    public void testGetNotifications() {
        //Not implemented yet
    }

    /**
     * Test of getAppInfoList method, of class AppInfoController.
     */
    @Test
    public void testGetAppInfoList() {
        //Not implemented yet
    }

    /**
     * Test of createAppInfo method, of class AppInfoController.
     */
    @Test
    public void testCreateAppInfo()  {
        try {
            this.mockMvc.perform(get("/appinfo/create"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(view().name("appinfo/appinfo_form"))
                    .andExpect(model().attributeExists("appinfo"));
        } catch (Exception ex) {
            Logger.getLogger(AppInfoControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of editAppInfo method, of class AppInfoController.
     */
    @Test
    public void testEditAppInfo() {
        //Not implemented yet
    }

    /**
     * Test of updateAppInfo method, of class AppInfoController.
     */
    @Test
    public void testUpdateAppInfo() {
        //Not implemented yet
    }

    /**
     * Test of deleteAppInfo method, of class AppInfoController.
     */
    @Test
    public void testDeleteAppInfo() {
        //Not implemented yet
    }

    private List<AppInfo> stubList() {
        List<AppInfo> appInfoList = new ArrayList<>();
        AppInfo item1 = new AppInfo();
        item1.setId(1L);
        item1.setHearthbeatInterval(25);
        item1.setName("item1");
        item1.setPath("liverpool");
        item1.setPort("6060");
        item1.setProtocol("https");
        item1.setUnresponsiveInterval(25);
        item1.setUrl("localhost");
        appInfoList.add(item1);
        AppInfo item2 = new AppInfo();
        item2.setId(2L);
        item2.setHearthbeatInterval(455);
        item2.setName("item2");
        item2.setPath("aston villa");
        item2.setPort("9090");
        item2.setProtocol("http");
        item2.setUnresponsiveInterval(987);
        item2.setUrl("192.168.56.35");
        appInfoList.add(item2);

        return appInfoList;
    }

}
