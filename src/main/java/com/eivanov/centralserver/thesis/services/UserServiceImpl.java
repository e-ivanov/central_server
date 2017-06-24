/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.services;

import com.eivanov.centralserver.thesis.entity.AppInfo;
import com.eivanov.centralserver.thesis.entity.NotificationGroup;
import com.eivanov.centralserver.thesis.entity.Server;
import com.eivanov.centralserver.thesis.entity.User;
import com.eivanov.centralserver.thesis.entity.UserRole;
import com.eivanov.centralserver.thesis.repository.UserRepository;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author killer
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServerService serverService;

    @Autowired
    private AppInfoService appInfoService;

    @Autowired
    private UserRolesService userRolesService;

    @Autowired
    private NotificationGroupService notificationGroupService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Secured("ROLE_ADMIN")
    @Override
    public void delete(User entity) {
        for (Server server : entity.getServerSet()) {
            server.getUserSet().remove(entity);
            serverService.save(server);
        }
        for (AppInfo appInfo : entity.getAppInfoSet()) {
            appInfo.getUserSet().remove(entity);
            appInfoService.save(appInfo);
        }
        for (NotificationGroup group : entity.getNotificationGroupSet()) {
            group.getUserSet().remove(entity);
            notificationGroupService.save(group);
        }
        for (UserRole role : entity.getUserRoles()) {
            role.getUsers().remove(entity);
            userRolesService.save(role);
        }
        userRepository.delete(entity);

    }

    @Secured("ROLE_ADMIN")
    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User load(long id) {
        return userRepository.load(id);
    }

    @Override
    public List<User> loadAll() {
        return userRepository.loadAll();
    }

    @Override
    public void save(User entity) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.getId().equals(entity.getId()) && !hasRole("ROLE_ADMIN")) {
            return;
        }

        if (entity.getId() != null) {
            User userDB = userRepository.load(entity.getId());
            updateServers(entity, userDB);
            updateNotificationGroups(entity, userDB);
            updateAppInfo(entity, userDB);
            userDB.setPassword(passwordEncoder.encode(entity.getPassword()));
            userDB.setEmail(entity.getEmail());
            userDB.setFirstName(entity.getFirstName());
            userDB.setLastName(entity.getLastName());
            userDB.setPhone(entity.getPhone());
            if (entity.getAppInfoSet() != null) {
                userDB.setAppInfoSet(entity.getAppInfoSet());
            }
            if (entity.getNotificationGroupSet() != null) {
                userDB.setNotificationGroupSet(entity.getNotificationGroupSet());
            }
            if (entity.getServerSet() != null) {
                userDB.setServerSet(entity.getServerSet());
            }
            if (entity.getUserRoles() != null) {
                userDB.setUserRoles(entity.getUserRoles());
            }
            userRepository.save(userDB);
        } else {
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
            User finalUser = (User)userRepository.save(entity);
            for (Server server : finalUser.getServerSet()) {
                server.getUserSet().add(finalUser);
                serverService.save(server);
            }
            for (AppInfo info : finalUser.getAppInfoSet()) {
                info.getUserSet().add(finalUser);
                appInfoService.save(info);
            }

            for (NotificationGroup group : finalUser.getNotificationGroupSet()) {
                group.getUserSet().add(finalUser);
                notificationGroupService.save(group);
            }
        }

    }

    private void updateServers(User newEntity, User oldEntity) {
        Set<Server> removedServers = Sets.difference(oldEntity.getServerSet(), newEntity.getServerSet());
        Set<Server> newServers = Sets.difference(newEntity.getServerSet(), oldEntity.getServerSet());
        for (Server server : removedServers) {
            server.getUserSet().remove(oldEntity);
            serverService.save(server);
        }
        for (Server server : newServers) {
            server.getUserSet().add(oldEntity);
            serverService.save(server);
        }
    }

    private void updateNotificationGroups(User newEntity, User oldEntity) {
        Set<NotificationGroup> removedNotificationGroups = Sets.difference(oldEntity.getNotificationGroupSet(), newEntity.getNotificationGroupSet());
        Set<NotificationGroup> newNotificationGroups = Sets.difference(newEntity.getNotificationGroupSet(), oldEntity.getNotificationGroupSet());
        for (NotificationGroup group : removedNotificationGroups) {
            group.getUserSet().remove(oldEntity);
            notificationGroupService.save(group);
        }
        for (NotificationGroup group : newNotificationGroups) {
            group.getUserSet().add(oldEntity);
            notificationGroupService.save(group);
        }
    }

    private void updateAppInfo(User newEntity, User oldEntity) {
        Set<AppInfo> removedNotificationGroups = Sets.difference(oldEntity.getAppInfoSet(), newEntity.getAppInfoSet());
        Set<AppInfo> newNotificationGroups = Sets.difference(newEntity.getAppInfoSet(), oldEntity.getAppInfoSet());
        for (AppInfo appinfo : removedNotificationGroups) {
            appinfo.getUserSet().remove(oldEntity);
            appInfoService.save(appinfo);
        }
        for (AppInfo appinfo : newNotificationGroups) {
            appinfo.getUserSet().add(oldEntity);
            appInfoService.save(appinfo);
        }
    }

    @Secured("ROLE_ADMIN")
    @Override
    public void saveAll(List<User> items) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User loadByUserName(String userName) {
        return userRepository.loadByUserName(userName);
    }

    private boolean hasRole(String roleToCheck) {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        boolean hasRole = false;
        for (GrantedAuthority authority : authorities) {
            hasRole = authority.getAuthority().equals(roleToCheck);
            if (hasRole) {
                break;
            }
        }
        return hasRole;
    }

}
