/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.web.controllers;

import com.eivanov.centralserver.thesis.entity.AppInfo;
import com.eivanov.centralserver.thesis.entity.EmailNotification;
import com.eivanov.centralserver.thesis.entity.NotificationChannel;
import com.eivanov.centralserver.thesis.entity.NotificationGroup;
import com.eivanov.centralserver.thesis.entity.User;
import com.eivanov.centralserver.thesis.services.AppInfoService;
import com.eivanov.centralserver.thesis.services.NotificationChannelService;
import com.eivanov.centralserver.thesis.services.NotificationGroupService;
import com.eivanov.centralserver.thesis.services.NotificationService;
import com.eivanov.centralserver.thesis.services.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    private NotificationChannelService notificationChannelService;
    
    @Autowired
    private NotificationGroupService notificationGroupService;
    
    @ModelAttribute("userList")
    private List<User> getUserList(){
        return userService.loadAll();
    }
    
    @ModelAttribute("notificatonList")
    private List<NotificationChannel> getNotificationChannels(){
        return notificationChannelService.loadAll();
    }
    
    @ModelAttribute("notificationGroupsList")
    private List<NotificationGroup> getNotificationGroups(){
        return notificationGroupService.loadAll();
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
    public String updateAppInfo(@Valid @ModelAttribute("appinfo") AppInfo appInfo, BindingResult result){
        if(result.hasErrors()){
            return "appinfo/appinfo_form";
        }
        appInfoService.save(appInfo);
        return "redirect:/appinfo/list";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteAppInfo(@PathVariable("id") long id){
        appInfoService.delete(appInfoService.load(id));
        return "redirect:/appinfo/list";
    }
}
