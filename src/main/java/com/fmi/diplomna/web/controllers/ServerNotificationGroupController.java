/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author killer
 */
@Controller
@RequestMapping("/servernotificationgroup")
public class ServerNotificationGroupController {
    
    @RequestMapping("/listNotificationGroups")
    public String getNotificationList(){
        return "none";
    }
    
}
