/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.services;

import com.fmi.diplomna.hibernate.EmailNotification;
import java.util.List;

/**
 *
 * @author killer
 */
public interface EmailNotificationService {
    
    void delete(EmailNotification entity);

    void deleteAll();

    EmailNotification load(long id);

    List<EmailNotification> loadAll();

    void save(EmailNotification entity);

    void saveAll(List<EmailNotification> items);
}
