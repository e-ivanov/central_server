
package com.eivanov.centralserver.thesis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.eivanov.centralserver.thesis.domain.mongo.CpuData;
import com.eivanov.centralserver.thesis.domain.mongo.DiskData;
import com.eivanov.centralserver.thesis.domain.mongo.MemoryData;
import com.eivanov.centralserver.thesis.domain.mongo.NetworkData;
import com.eivanov.centralserver.thesis.domain.mongo.ProcessData;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.DateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "memoryData",
    "cpuData",
    "diskData",
    "system_uptime",
    "networkData",
    "processList",
    "serverId",
    "uuid"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServerReadingMessage implements Serializable{

    private static final long serialVersionUID = 1L;

    
    @JsonProperty("memoryData")
    private MemoryData memoryData;
    @JsonProperty("cpuData")
    private CpuData cpuData;
    @JsonProperty("diskData")
    private DiskData diskData;
    @JsonProperty("system_uptime")
    private String systemUptime;
    @JsonProperty("networkData")
    private NetworkData networkData;
    @JsonProperty("processList")
    @java.lang.SuppressWarnings("squid:S1948")
    private List<ProcessData> processList = null;
    @JsonProperty("serverId")
    private int serverId;
    @JsonProperty("uuid")
    private String uuid;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ServerReadingMessage() {
    }

    /**
     * 
     * @param diskData
     * @param cpuData
     * @param processList
     * @param networkData
     * @param serverId
     * @param memoryData
     * @param systemUptime
     */
    public ServerReadingMessage(MemoryData memoryData, CpuData cpuData,
                                DiskData diskData, String systemUptime,
                                NetworkData networkData,
                                List<ProcessData> processList,
                                int serverId, String uuid) {
        super();
        this.memoryData = memoryData;
        this.cpuData = cpuData;
        this.diskData = diskData;
        this.systemUptime = systemUptime;
        this.networkData = networkData;
        this.processList = processList;
        this.serverId = serverId;
        this.uuid = uuid;
    }

    @JsonProperty("memoryData")
    public MemoryData getMemoryData() {
        return memoryData;
    }

    @JsonProperty("memoryData")
    public void setMemoryData(MemoryData memoryData) {
        this.memoryData = memoryData;
    }

    @JsonProperty("cpuData")
    public CpuData getCpuData() {
        return cpuData;
    }

    @JsonProperty("cpuData")
    public void setCpuData(CpuData cpuData) {
        this.cpuData = cpuData;
    }

    @JsonProperty("diskData")
    public DiskData getDiskData() {
        return diskData;
    }

    @JsonProperty("diskData")
    public void setDiskData(DiskData diskData) {
        this.diskData = diskData;
    }

    @JsonProperty("system_uptime")
    public String getSystemUptime() {
        return systemUptime;
    }

    @JsonProperty("system_uptime")
    public void setSystemUptime(String systemUptime) {
        this.systemUptime = systemUptime;
    }

    @JsonProperty("networkData")
    public NetworkData getNetworkData() {
        return networkData;
    }

    @JsonProperty("networkData")
    public void setNetworkData(NetworkData networkData) {
        this.networkData = networkData;
    }

    @JsonProperty("processList")
    public List<ProcessData> getProcessList() {
        return processList;
    }

    @JsonProperty("processList")
    public void setProcessList(List<ProcessData> processList) {
        this.processList = processList;
    }

    @JsonProperty("serverId")
    public int getServerId() {
        return serverId;
    }

    @JsonProperty("serverId")
    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    @JsonProperty("uuid")
    public String getUuid() {
        return uuid;
    }

    @JsonProperty("uuid")
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(memoryData).append(cpuData)
                .append(diskData).append(systemUptime).append(networkData)
                .append(processList).append(serverId).append(uuid)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ServerReadingMessage) == false) {
            return false;
        }
        ServerReadingMessage rhs = ((ServerReadingMessage) other);
        return new EqualsBuilder().append(memoryData, rhs.memoryData)
                .append(cpuData, rhs.cpuData).append(diskData, rhs.diskData)
                .append(systemUptime, rhs.systemUptime)
                .append(networkData, rhs.networkData)
                .append(processList, rhs.processList)
                .append(serverId, rhs.serverId)
                .append(uuid, rhs.uuid)
                .isEquals();
    }

}
