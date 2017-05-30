/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.hibernate;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author killer
 */
@Entity
@Table(name = "app_info")
@NamedQueries({
    @NamedQuery(name = "AppInfo.findAll", query = "SELECT a FROM AppInfo a")})
public class AppInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(min = 2, max = 30)
    @Column(name = "name", nullable = false)
    private String name;
    
    @NotNull @Size(min=4, max=5) //http или https
    @Column(name = "protocol", nullable = false)
    private String protocol;
    
    @NotEmpty @Size(max = 255)
    @Column(name = "url", nullable = false)
    private String url;
    
    @Size(max = 255)
    @Column(name = "path", nullable = true)
    private String path;
    
    @Size(max = 5) 
    @Column(name = "port", nullable = true)
    private String port;
    
    @NotEmpty @Min(1) @Max(1000)
    @Column(name = "hearthbeat_interval", nullable = false)
    private int hearthbeatInterval;
    
    @NotEmpty @Min(1) @Max(1000)
    @Column(name = "unresponsive_interval", nullable = false)
    private int unresponsiveInterval;
    @Column(name = "notification_interval", nullable = false)
    private int notificationInterval;
    @JoinTable(name = "app_info_has_user", joinColumns = {
        @JoinColumn(name = "app_info_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id")})
    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Set<User> userSet;
    @JoinTable(name = "app_info_has_notification_channel", joinColumns = {
        @JoinColumn(name = "app_info_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "notification_channel_id", referencedColumnName = "id")})
    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Set<NotificationChannel> notificationChannelSet;
    @JoinTable(name = "app_info_has_notification_group", joinColumns = {
        @JoinColumn(name = "app_info_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "notification_group_id", referencedColumnName = "id")})
    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Set<NotificationGroup> notificationGroupSet;

    public AppInfo() {
    }

    public AppInfo(Long id) {
        this.id = id;
    }

    public AppInfo(Long id, String name, String url, String port, int hearthbeatInterval, int unresponsiveInterval) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.port = port;
        this.hearthbeatInterval = hearthbeatInterval;
        this.unresponsiveInterval = unresponsiveInterval;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getHearthbeatInterval() {
        return hearthbeatInterval;
    }

    public void setHearthbeatInterval(int hearthbeatInterval) {
        this.hearthbeatInterval = hearthbeatInterval;
    }

    public int getUnresponsiveInterval() {
        return unresponsiveInterval;
    }

    public void setUnresponsiveInterval(int unresponsiveInterval) {
        this.unresponsiveInterval = unresponsiveInterval;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public Set<NotificationChannel> getNotificationChannelSet() {
        return notificationChannelSet;
    }

    public void setNotificationChannelSet(Set<NotificationChannel> notificationChannelSet) {
        this.notificationChannelSet = notificationChannelSet;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getNotificationInterval() {
        return notificationInterval;
    }

    public void setNotificationInterval(int notificationInterval) {
        this.notificationInterval = notificationInterval;
    }

    public Set<NotificationGroup> getNotificationGroupSet() {
        return notificationGroupSet;
    }

    public void setNotificationGroupSet(Set<NotificationGroup> notificationGroupSet) {
        this.notificationGroupSet = notificationGroupSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppInfo)) {
            return false;
        }
        AppInfo other = (AppInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "com.fmi.diplomna.hibernate.AppInfo[ id=" + id + " ]";
    }

}
