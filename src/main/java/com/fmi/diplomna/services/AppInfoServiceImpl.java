/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.services;

import com.fmi.diplomna.hibernate.AppInfo;
import com.fmi.diplomna.hibernate.User;
import com.fmi.diplomna.repository.AppInfoRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AppInfoServiceImpl implements AppInfoService {
    
    @Autowired
    private AppInfoRepository appInfoRepository;
    
    @Autowired
    private UserService userService;
    
    private static final Logger logger = LoggerFactory.getLogger(AppInfoServiceImpl.class);
    
    @Override
    public void save(AppInfo entity) {
        appInfoRepository.save(entity);
    }

    
    @Override
    public AppInfo load(long id) {
        return appInfoRepository.load(id);
    }

    
    @Override
    public void delete(AppInfo entity) {
        for(User user : entity.getUserSet()){
            user.getAppInfoSet().remove(entity);
            userService.save(user);
        }
        appInfoRepository.delete(entity);
    }

    
    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public List<AppInfo> loadAll() {
        return appInfoRepository.loadAll();
    }

    
    @Override
    public void saveAll(List<AppInfo> items) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
