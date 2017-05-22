/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.web.controllers;

import com.fmi.diplomna.hibernate.EmailNotification;
import com.fmi.diplomna.repository.EmailNotificationRepository;
import com.fmi.diplomna.services.EmailNotificationService;
import com.fmi.diplomna.web.response.AjaxFormResponse;
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
    private EmailNotificationService emailNotificationService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listNotificationChannels(Model model) {
        model.addAttribute("emailnotificationlist", emailNotificationService.loadAll());
        model.addAttribute("newEmailNotification", new EmailNotification());
        return "notification_channel/list";
    }

    @RequestMapping(value = "/create/email", method = RequestMethod.POST)
    @ResponseBody
    public AjaxFormResponse createEmailChannel(
            @ModelAttribute("newEmailNotification") EmailNotification notification,
            BindingResult bindingResult) {
        AjaxFormResponse response = new AjaxFormResponse();
        if (bindingResult.hasErrors()) {
            response.setStatus("FAIL");
            response.setErrorMessageList(bindingResult.getFieldErrors());
            return response;
        } else {
            emailNotificationService.save(notification);
            response.setStatus("SUCCESS");
            response.setPayload(notification);
        }
        return response;

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxFormResponse updateEmailChannel(
            @ModelAttribute("emailChannel") EmailNotification notification,
            BindingResult bindingResult) {

        AjaxFormResponse response = new AjaxFormResponse();
        if (bindingResult.hasErrors()) {
            response.setStatus("FAIL");
            response.setErrorMessageList(bindingResult.getFieldErrors());
            return response;
        } else {
            emailNotificationService.save(notification);
            response.setStatus("SUCCESS");
            response.setPayload(notification);
        }
        return response;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public AjaxFormResponse deleteEmailChannel(
            @ModelAttribute("emailChannel") EmailNotification notification,
            BindingResult bindingResult
    ) {
        AjaxFormResponse response = new AjaxFormResponse();
        emailNotificationService.delete(notification);
        return response;
    }
}
