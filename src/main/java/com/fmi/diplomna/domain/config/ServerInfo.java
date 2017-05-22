/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.domain.config;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author killer
 */
@Document(collection = "servers")
public class ServerInfo {
    @Id
    private String id;
    private String ipAddress;
    private String domain;
    private String name;

    public ServerInfo() {
    }

    
    public ServerInfo(String id, String ipAddress, String domain, String name) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.domain = domain;
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
