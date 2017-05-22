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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author killer
 */
@Entity
@Table(name = "notification_channel")
@Inheritance(strategy = InheritanceType.JOINED)
public class NotificationChannel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    protected Long id;
    @Basic(optional = false)
    @Column(name = "channel_name")
    protected String channelName;
    @ManyToMany(cascade = {CascadeType.PERSIST}, mappedBy = "notificationChannelSet", fetch = FetchType.EAGER)
    protected Set<AppInfo> appInfoSet;
    @ManyToMany(cascade = {CascadeType.PERSIST}, mappedBy = "notificationChannelSet", fetch = FetchType.EAGER)
    protected Set<ResourceNotificationPolicy> resourceNotificationPolicySet;

    public NotificationChannel() {
    }

    public NotificationChannel(Long id) {
        this.id = id;
    }

    public NotificationChannel(Long id, String channelName) {
        this.id = id;
        this.channelName = channelName;
    }

    public Long getId() {
        return id;
    }
    
     public void setId(Long id) {
        this.id = id;
    }


    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Set<AppInfo> getAppInfoSet() {
        return appInfoSet;
    }

    public void setAppInfoSet(Set<AppInfo> appInfoSet) {
        this.appInfoSet = appInfoSet;
    }

    public Set<ResourceNotificationPolicy> getResourceNotificationPolicySet() {
        return resourceNotificationPolicySet;
    }

    public void setResourceNotificationPolicySet(Set<ResourceNotificationPolicy> resourceNotificationPolicySet) {
        this.resourceNotificationPolicySet = resourceNotificationPolicySet;
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
        if (!(object instanceof NotificationChannel)) {
            return false;
        }
        NotificationChannel other = (NotificationChannel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fmi.diplomna.hibernate.NotificationChannel[ id=" + id + " ]";
    }
    
}
