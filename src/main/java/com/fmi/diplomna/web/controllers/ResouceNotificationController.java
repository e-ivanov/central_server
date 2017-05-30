/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.web.controllers;

import com.fmi.diplomna.hibernate.EmailNotification;
import com.fmi.diplomna.hibernate.NotificationChannel;
import com.fmi.diplomna.hibernate.ResourceNotificationPolicy;
import com.fmi.diplomna.hibernate.Server;
import com.fmi.diplomna.repository.ResourceNotificationPolicyRepository;
import com.fmi.diplomna.repository.ServerRepository;
import com.fmi.diplomna.services.NotificationChannelService;
import com.fmi.diplomna.services.ResourceNotificationPolicyService;
import com.fmi.diplomna.services.ServerService;
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
            @ModelAttribute("rsnotification") ResourceNotificationPolicy policy){
        notificationPolicyService.save(policy);
        return "redirect:list";
    }
    
    @RequestMapping("/edit/{id}")
    public String editResourceNotificationPolicy(@PathVariable("id") long id,Model model){
        model.addAttribute("rspolicy", notificationPolicyService.load(id));
        return "rsnotification/rsnotification_form";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteResourceNotificationPolicy(@PathVariable("id") long id){
        notificationPolicyService.delete(notificationPolicyService.load(id));
        return "redirect:list";
    }
}
