/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.hibernate;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author killer
 */
@Entity
@Table(name = "resource_notification_policy")
@NamedQueries({
    @NamedQuery(name = "ResourceNotificationPolicy.findAll", query = "SELECT r FROM ResourceNotificationPolicy r")})
public class ResourceNotificationPolicy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    private Measure cpuResourceConfig;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "warnLevel", column = @Column(name = "memory_warn_level")),
        @AttributeOverride(name = "criticalLevel", column = @Column(name = "memory_critical_level")),
        @AttributeOverride(name = "notificationInverval", column = @Column(name = "memory_notification_interval"))})
    private Measure memoryResourceConfig;
        
    @Embedded
    @AttributeOverrides({
    @AttributeOverride(name = "warnLevel", column = @Column(name = "disk_usage_warn_level")),
    @AttributeOverride(name = "criticalLevel", column = @Column(name = "disk_usage_critical_level")),
    @AttributeOverride(name = "notificationInverval", column = @Column(name = "disk_usage_notification_interval"))})
    private Measure diskUsageResourceConfig;
    
    @Basic(optional = false)
    @Column(name = "server_unresponsive_interval")
    private int serverUnresponsiveInterval;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @JoinTable(name = "notification_policy_has_notification_channel", joinColumns = {
        @JoinColumn(name = "notification_policy_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "notification_channel_id", referencedColumnName = "id")})
    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Set<NotificationChannel> notificationChannelSet;
    @OneToMany(mappedBy = "notificationPolicy", fetch = FetchType.EAGER)
    private Set<Server> serverSet;

    public ResourceNotificationPolicy() {
    }

    public ResourceNotificationPolicy(Long id) {
        this.id = id;
    }

    public ResourceNotificationPolicy(Long id, Measure cpuResourceConfig, Measure memoryResourceConfig, Measure diskUsageResourceConfig, int serverUnresponsiveInterval, String name, Set<NotificationChannel> notificationChannelSet, Set<Server> serverSet) {
        this.id = id;
        this.cpuResourceConfig = cpuResourceConfig;
        this.memoryResourceConfig = memoryResourceConfig;
        this.diskUsageResourceConfig = diskUsageResourceConfig;
        this.serverUnresponsiveInterval = serverUnresponsiveInterval;
        this.name = name;
        this.notificationChannelSet = notificationChannelSet;
        this.serverSet = serverSet;
    }

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Measure getCpuResourceConfig() {
        return cpuResourceConfig;
    }

    public void setCpuResourceConfig(Measure cpuResourceConfig) {
        this.cpuResourceConfig = cpuResourceConfig;
    }


    public Measure getMemoryResourceConfig() {
        return memoryResourceConfig;
    }

    public void setMemoryResourceConfig(Measure memoryResourceConfig) {
        this.memoryResourceConfig = memoryResourceConfig;
    }

    public Measure getDiskUsageResourceConfig() {
        return diskUsageResourceConfig;
    }

    public void setDiskUsageResourceConfig(Measure diskUsageResourceConfig) {
        this.diskUsageResourceConfig = diskUsageResourceConfig;
    }


    

    public int getServerUnresponsiveInterval() {
        return serverUnresponsiveInterval;
    }

    public void setServerUnresponsiveInterval(int serverUnresponsiveInterval) {
        this.serverUnresponsiveInterval = serverUnresponsiveInterval;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<NotificationChannel> getNotificationChannelSet() {
        return notificationChannelSet;
    }

    public void setNotificationChannelSet(Set<NotificationChannel> notificationChannelSet) {
        this.notificationChannelSet = notificationChannelSet;
    }

    public Set<Server> getServerSet() {
        return serverSet;
    }

    public void setServerSet(Set<Server> serverSet) {
        this.serverSet = serverSet;
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
        if (!(object instanceof ResourceNotificationPolicy)) {
            return false;
        }
        ResourceNotificationPolicy other = (ResourceNotificationPolicy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fmi.diplomna.hibernate.ResourceNotificationPolicy[ id=" + id + " ]";
    }

}
