/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.web.controllers;

import com.eivanov.centralserver.thesis.entity.EmailNotification;
import com.eivanov.centralserver.thesis.entity.NotificationChannel;
import com.eivanov.centralserver.thesis.entity.NotificationChannelFactory;
import com.eivanov.centralserver.thesis.services.NotificationChannelService;
import com.eivanov.centralserver.thesis.web.response.AjaxFormResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author killer
 */
@Controller
@RequestMapping("/notificationChannel")
public class NotificationChannelController {

    @Autowired
    private NotificationChannelService notificationChannelService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listNotificationChannels(Model model) {
        model.addAttribute("emailnotificationlist", notificationChannelService.getEmailNotifications());
        return "notification_channel/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxFormResponse createChannel(HttpServletRequest request) {
        AjaxFormResponse response = new AjaxFormResponse();
        NotificationChannel channel = NotificationChannelFactory.createNotificationChannel(request);
//        if (bindingResult.hasErrors()) {
//            response.setStatus("FAIL");
//            response.setErrorMessageList(bindingResult.getFieldErrors());
//            return response;
//        } else {
            notificationChannelService.save(channel);
            response.setStatus("SUCCESS");
            response.setPayload(channel);
//        }
        return response;

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxFormResponse updateChannel(HttpServletRequest request) {

        AjaxFormResponse response = new AjaxFormResponse();
        NotificationChannel channel = NotificationChannelFactory.createNotificationChannel(request);
//        if (bindingResult.hasErrors()) {
//            response.setStatus("FAIL");
//            response.setErrorMessageList(bindingResult.getFieldErrors());
//            return response;
//        } else {
            notificationChannelService.save(channel);
            response.setStatus("SUCCESS");
            response.setPayload(channel);
//        }
        return response;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public AjaxFormResponse deleteChannel(HttpServletRequest request) {
        AjaxFormResponse response = new AjaxFormResponse();
        NotificationChannel channel = NotificationChannelFactory.createNotificationChannel(request);
        notificationChannelService.delete(notificationChannelService.load(channel.getId()));
        return response;
    }
}
