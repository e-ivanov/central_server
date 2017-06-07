/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.domain.config;

import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author killer
 */
@Document(collection = "application_ping_info")
public class ApplicationPingConfig {

    @Id
    private String id;
    private String name;
    private String serverId;
    private int port;
    private long unresponsiveInterval;
    private long hearthBeatInterval;

    public ApplicationPingConfig() {
    }

    public ApplicationPingConfig(String name, String serverId, int port, long unresponsiveInterval, long hearthBeatInterval) {
        this.name = name;
        this.serverId = serverId;
        this.port = port;
        this.unresponsiveInterval = unresponsiveInterval;
        this.hearthBeatInterval = hearthBeatInterval;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public long getUnresponsiveInterval() {
        return unresponsiveInterval;
    }

    public void setUnresponsiveInterval(long unresponsiveInterval) {
        this.unresponsiveInterval = unresponsiveInterval;
    }

    public long getHearthBeatInterval() {
        return hearthBeatInterval;
    }

    public void setHearthBeatInterval(long hearthBeatInterval) {
        this.hearthBeatInterval = hearthBeatInterval;
    }

}
