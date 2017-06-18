/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.web.controllers;

import com.eivanov.centralserver.thesis.entity.EmailNotification;
import com.eivanov.centralserver.thesis.entity.NotificationChannel;
import com.eivanov.centralserver.thesis.entity.ResourceNotificationPolicy;
import com.eivanov.centralserver.thesis.entity.Server;
import com.eivanov.centralserver.thesis.repository.ResourceNotificationPolicyRepository;
import com.eivanov.centralserver.thesis.repository.ServerRepository;
import com.eivanov.centralserver.thesis.services.NotificationChannelService;
import com.eivanov.centralserver.thesis.services.ResourceNotificationPolicyService;
import com.eivanov.centralserver.thesis.services.ServerService;
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
@RequestMapping("/resourcePolicy")
public class ResouceNotificationController {
    
    @Autowired
    private ResourceNotificationPolicyService notificationPolicyService;
    
    @Autowired
    private ServerService serverService;
    
    @Autowired
    private NotificationChannelService notificationChannelService;
    
    @ModelAttribute("serverlist")
    public List<Server> getServerList(){
        return serverService.loadAll();
    }
    
    @ModelAttribute("notificationchannellist")
    public List<NotificationChannel> getNotificationChannels(){
        return notificationChannelService.loadAll();
    }
    
    @RequestMapping("/list")
    public String listResourceNotifications(Model model){
        model.addAttribute("rsnotificationlist", notificationPolicyService.loadAll());
        return "rsnotification/list";
    }
    
    @RequestMapping("/create")
    public String createResourceNotificationPolicy(Model model){
        model.addAttribute("rspolicy", new ResourceNotificationPolicy());
        return "rsnotification/rsnotification_form";
    }
    
    @RequestMapping(value="/update", method=RequestMethod.POST)
    public String updateResourceNotificationPolicy(
            @Valid @ModelAttribute("rsnotification") ResourceNotificationPolicy policy,
            BindingResult result){
        if(result.hasErrors()){
            return "rsnotification/rsnotification_form";
        }
        notificationPolicyService.save(policy);
        return "redirect:/resourcePolicy/list";
    }
    
    @RequestMapping("/edit/{id}")
    public String editResourceNotificationPolicy(@PathVariable("id") long id,Model model){
        model.addAttribute("rspolicy", notificationPolicyService.load(id));
        return "rsnotification/rsnotification_form";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteResourceNotificationPolicy(@PathVariable("id") long id){
        notificationPolicyService.delete(notificationPolicyService.load(id));
        return "redirect:/resourcePolicy/list";
    }
}
