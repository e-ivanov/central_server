/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.listeners;

import com.eivanov.centralserver.thesis.dto.NotificationDTO;

/**
 *
 * @author killer
 */
public class NotificationFactory {
    
    public static NotificationDTO buildNotification(){
        NotificationDTO dto = new NotificationDTO();
        
        return dto;
    }
    
}
