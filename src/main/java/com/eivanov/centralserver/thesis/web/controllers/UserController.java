/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.web.controllers;

import com.eivanov.centralserver.thesis.entity.AppInfo;
import com.eivanov.centralserver.thesis.entity.NotificationGroup;
import com.eivanov.centralserver.thesis.entity.Server;
import com.eivanov.centralserver.thesis.entity.User;
import com.eivanov.centralserver.thesis.entity.UserRole;
import com.eivanov.centralserver.thesis.repository.ServerRepository;
import com.eivanov.centralserver.thesis.repository.UserRepository;
import com.eivanov.centralserver.thesis.repository.UserRolesRepository;
import com.eivanov.centralserver.thesis.services.AppInfoService;
import com.eivanov.centralserver.thesis.services.NotificationGroupService;
import com.eivanov.centralserver.thesis.services.ServerService;
import com.eivanov.centralserver.thesis.services.UserRolesService;
import com.eivanov.centralserver.thesis.services.UserService;
import java.util.ArrayList;
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
    public String updateUser(@Valid @ModelAttribute("user") User user,
                             BindingResult result) {
        if(result.hasErrors()){
            return "user/user_form";
        }
        userService.save(user);
        return "redirect:/user/list";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("id") long id){
        userService.delete(userService.load(id));
        return "redirect:/user/list";
    }

}
