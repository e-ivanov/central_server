/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.web.controllers;

import com.fmi.diplomna.hibernate.AppInfo;
import com.fmi.diplomna.hibernate.EmailNotification;
import com.fmi.diplomna.hibernate.User;
import com.fmi.diplomna.services.AppInfoService;
import com.fmi.diplomna.services.EmailNotificationService;
import com.fmi.diplomna.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author killer
 */
@Controller
@RequestMapping("/appinfo")
public class AppInfoController {

    @Autowired
    private AppInfoService appInfoService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private EmailNotificationService emailNotificationService;
    
    @ModelAttribute("userList")
    private List<User> getUserList(){
        return userService.loadAll();
    }
    
    @ModelAttribute("notificatonList")
    private List<EmailNotification> getNotifications(){
        return emailNotificationService.loadAll();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getAppInfoList(Model model) {
        model.addAttribute("appinfolist", appInfoService.loadAll());
        return "appinfo/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createAppInfo(Model model) {
        model.addAttribute("appinfo", new AppInfo());
        return "appinfo/appinfo_form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAppInfo(@PathVariable("id") long id, Model model) {
        model.addAttribute("appinfo", appInfoService.load(id));
        return "appinfo/appinfo_form";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateAppInfo(@ModelAttribute("appinfo") AppInfo appInfo){
        appInfoService.save(appInfo);
        return "redirect:list";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteAppInfo(@PathVariable("id") long id){
        appInfoService.delete(appInfoService.load(id));
        return "redirect:list";
    }
}
