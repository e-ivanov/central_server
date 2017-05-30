/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.web.controllers;

import com.fmi.diplomna.hibernate.NotificationGroup;
import com.fmi.diplomna.hibernate.ResourceNotificationPolicy;
import com.fmi.diplomna.hibernate.Server;
import com.fmi.diplomna.hibernate.User;
import com.fmi.diplomna.repository.SensorReadingRepository;
import com.fmi.diplomna.services.NotificationGroupService;
import com.fmi.diplomna.services.ResourceNotificationPolicyService;
import com.fmi.diplomna.services.ServerService;
import com.fmi.diplomna.services.UserService;
import java.util.List;
import org.hibernate.sql.Template;
import org.joda.time.DateTime;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/server")
public class ServerController {

    
    @Value("${rabbitmq.server_ip}")
    private String rabbitAddress;
    
    @Autowired
    private ServerService serverService;
    
    @Autowired
    private ResourceNotificationPolicyService notificationPolicyService;
    
    @Autowired
    private NotificationGroupService notificationGroupService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private SensorReadingRepository sensorReadingRepository;
    
    

    @ModelAttribute("rsnotificationslist")
    public List<ResourceNotificationPolicy> getResourceNotificationsList(){
        return notificationPolicyService.loadAll();
    }
    
    @ModelAttribute("notificationGroups")
    public List<NotificationGroup> getNotificationGroups(){
        return notificationGroupService.loadAll();
    }
    
    @ModelAttribute("userNotifications")
    public List<User> getNotifiedUsers(){
        return userService.loadAll();
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listServers(Model model){
        model.addAttribute("servers", serverService.loadAll());
        return "server/list";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createServer(Model model){
        model.addAttribute("server", new Server());
        model.addAttribute("notificationPolicyList", notificationPolicyService.loadAll());
        return "server/server_form";
    }
    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewServer(@PathVariable("id") long id, Model model){
        DateTime date = DateTime.now().minusHours(7);
        model.addAttribute("perf_data", sensorReadingRepository.filterByServerAndDate(id, date));
        model.addAttribute("server_id", id);
        model.addAttribute("rabbit_address",rabbitAddress);
        return "server/view";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editServer(@PathVariable("id") long id, Model model) {
        Server server = serverService.load(id);
        model.addAttribute("server", server);
        model.addAttribute("notificationPolicyList", notificationPolicyService.loadAll());
        return "server/server_form";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteServer(@PathVariable("id") long id){
        serverService.delete(serverService.load(id));
        return "redirect:list";
    }
    
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String updateServer(@ModelAttribute("server") Server server){
        serverService.save(server);
        return "redirect:list";
    }
}
