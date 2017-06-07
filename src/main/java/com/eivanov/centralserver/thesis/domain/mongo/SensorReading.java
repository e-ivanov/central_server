
package com.eivanov.centralserver.thesis.domain.mongo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import javax.persistence.Id;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "memoryData",
    "cpuData",
    "diskData",
    "system_uptime",
    "networkData",
    "processList",
    "serverId",
    "timestamp",
    "uuid"
})

@Document(collection="sensor_reading")
public class SensorReading implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private MemoryData memoryData;
    private CpuData cpuData;
    private DiskData diskData;
    private String systemUptime;
    private NetworkData networkData;
    private long serverId;
    private DateTime timestamp;
    private String uuid;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SensorReading() {
    }

    public SensorReading(String id, MemoryData memoryData, CpuData cpuData, DiskData diskData, String systemUptime, NetworkData networkData, long serverId, DateTime timestamp, String uuid) {
        this.id = id;
        this.memoryData = memoryData;
        this.cpuData = cpuData;
        this.diskData = diskData;
        this.systemUptime = systemUptime;
        this.networkData = networkData;
        this.serverId = serverId;
        this.timestamp = timestamp;
        this.uuid = uuid;
    }




    public MemoryData getMemoryData() {
        return memoryData;
    }


    public void setMemoryData(MemoryData memoryData) {
        this.memoryData = memoryData;
    }


    public CpuData getCpuData() {
        return cpuData;
    }


    public void setCpuData(CpuData cpuData) {
        this.cpuData = cpuData;
    }


    public DiskData getDiskData() {
        return diskData;
    }


    public void setDiskData(DiskData diskData) {
        this.diskData = diskData;
    }


    public String getSystemUptime() {
        return systemUptime;
    }


    public void setSystemUptime(String systemUptime) {
        this.systemUptime = systemUptime;
    }


    public NetworkData getNetworkData() {
        return networkData;
    }


    public void setNetworkData(NetworkData networkData) {
        this.networkData = networkData;
    }

    public long getServerId() {
        return serverId;
    }


    public void setServerId(long serverId) {
        this.serverId = serverId;
    }

    public String getId() {
        return id;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getUuid() {
        return uuid;
    }

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
                 .append(serverId).append(uuid).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SensorReading) == false) {
            return false;
        }
        SensorReading rhs = ((SensorReading) other);
        return new EqualsBuilder().append(memoryData, rhs.memoryData)
                .append(cpuData, rhs.cpuData).append(diskData, rhs.diskData)
                .append(systemUptime, rhs.systemUptime)
                .append(networkData, rhs.networkData)
                .append(serverId, rhs.serverId)
                .append(uuid, rhs.uuid)
                .isEquals();
    }

}
