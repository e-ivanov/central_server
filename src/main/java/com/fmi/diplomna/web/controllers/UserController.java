/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.web.controllers;

import com.fmi.diplomna.hibernate.AppInfo;
import com.fmi.diplomna.hibernate.NotificationGroup;
import com.fmi.diplomna.hibernate.Server;
import com.fmi.diplomna.hibernate.User;
import com.fmi.diplomna.hibernate.UserRole;
import com.fmi.diplomna.repository.ServerRepository;
import com.fmi.diplomna.repository.UserRepository;
import com.fmi.diplomna.repository.UserRolesRepository;
import com.fmi.diplomna.services.AppInfoService;
import com.fmi.diplomna.services.NotificationGroupService;
import com.fmi.diplomna.services.ServerService;
import com.fmi.diplomna.services.UserRolesService;
import com.fmi.diplomna.services.UserService;
import java.util.ArrayList;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ServerService serverService;
    
    @Autowired
    private NotificationGroupService notificationGroupService;
    
    @Autowired
    private AppInfoService appInfoService;
    
    @Autowired
    private UserRolesService userRolesService;
    
    @ModelAttribute("serverList")
    public List<Server> getServerList(){
        return serverService.loadAll();
    }
    
    @ModelAttribute("rolesList")
    public List<UserRole> getUserRoles(){
        return userRolesService.getUserRoles();
    }
    
    @ModelAttribute("notificationGroupList")
    public List<NotificationGroup> getNotificationGroups(){
        return notificationGroupService.loadAll();
    }
    
    @ModelAttribute("appinfoList")
    public List<AppInfo> getAppInfoList(){
        return appInfoService.loadAll();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getUsersList(Model model) {
        model.addAttribute("users", userService.loadAll());
        return "user/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "user/user_form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.load(id));
        return "user/user_form";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:list";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("id") long id){
        userService.delete(userService.load(id));
        return "redirect:list";
    }

}
