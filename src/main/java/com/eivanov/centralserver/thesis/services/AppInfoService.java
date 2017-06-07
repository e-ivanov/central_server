/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.services;

import com.eivanov.centralserver.thesis.entity.AppInfo;
import java.util.List;

/**
 *
 * @author killer
 */
public interface AppInfoService {

    void delete(AppInfo entity);

    void deleteAll();

    AppInfo load(long id);

    List<AppInfo> loadAll();

    void save(AppInfo entity);

    void saveAll(List<AppInfo> items);
    
}
