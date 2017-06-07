/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.entity;

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

/**
 *
 * @author killer
 */
@Entity
@Table(name = "notification_group")
@NamedQueries({
    @NamedQuery(name = "NotificationGroup.findAll", query = "SELECT n FROM NotificationGroup n")})
public class NotificationGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @JoinTable(name = "server_has_notification_group", joinColumns = {
        @JoinColumn(name = "notification_group_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "server_id", referencedColumnName = "id")})
    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Set<Server> serverSet;
    @JoinTable(name = "user_has_notification_group", joinColumns = {
        @JoinColumn(name = "notification_group_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id")})
    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Set<User> userSet;
    @JoinTable(name = "app_info_has_notification_group", joinColumns = {
        @JoinColumn(name = "notification_group_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "app_info_id", referencedColumnName = "id")})
    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Set<AppInfo> appInfoSet;

    public NotificationGroup() {
    }

    public NotificationGroup(Long id) {
        this.id = id;
    }

    public NotificationGroup(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Server> getServerSet() {
        return serverSet;
    }

    public void setServerSet(Set<Server> serverSet) {
        this.serverSet = serverSet;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public Set<AppInfo> getAppInfoSet() {
        return appInfoSet;
    }

    public void setAppInfoSet(Set<AppInfo> appInfoSet) {
        this.appInfoSet = appInfoSet;
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
        if (!(object instanceof NotificationGroup)) {
            return false;
        }
        NotificationGroup other = (NotificationGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eivanov.centralserver.thesis.entity.NotificationGroup[ id=" + id + " ]";
    }

}
