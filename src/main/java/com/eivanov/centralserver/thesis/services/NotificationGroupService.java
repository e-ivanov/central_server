/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.services;

import com.eivanov.centralserver.thesis.entity.NotificationGroup;
import java.util.List;

/**
 *
 * @author killer
 */
public interface NotificationGroupService {
    
    void delete(NotificationGroup entity);

    void deleteAll();

    NotificationGroup load(long id);

    List<NotificationGroup> loadAll();

    void save(NotificationGroup entity);

    void saveAll(List<NotificationGroup> items);
}
