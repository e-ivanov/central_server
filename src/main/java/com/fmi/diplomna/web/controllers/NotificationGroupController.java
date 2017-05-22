/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.web.controllers;

import com.fmi.diplomna.hibernate.NotificationGroup;
import com.fmi.diplomna.hibernate.Server;
import com.fmi.diplomna.hibernate.User;
import com.fmi.diplomna.services.NotificationGroupService;
import com.fmi.diplomna.services.ServerService;
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
@RequestMapping("/notificationgroup")
public class NotificationGroupController {
    
    @Autowired
    private NotificationGroupService groupService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ServerService serverService;
    
    @ModelAttribute("userList")
    public List<User> getUsersList(){
        return userService.loadAll();
    }
    
    @ModelAttribute("serverList")
    public List<Server> getServerList(){
        return serverService.loadAll();
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getNotificationGroups(Model model){
        model.addAttribute("notificationGroups", groupService.loadAll());
        return "notification_group/list";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createNotificationGroup(Model model){
        model.addAttribute("group", new NotificationGroup());
        return "notification_group/ngroup_form";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editNotificationGroup(@PathVariable("id") long id, Model model){
        model.addAttribute("group", groupService.load(id));
        return "notification_group/ngroup_form";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateNotificationGroup(@ModelAttribute("group") NotificationGroup group){
        groupService.save(group);
        return "redirect:list";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteNotificationGroup(@PathVariable("id") long id){
        groupService.delete(groupService.load(id));
        return "redirect:list";
    }
}
