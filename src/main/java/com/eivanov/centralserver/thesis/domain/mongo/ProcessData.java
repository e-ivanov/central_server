
package com.eivanov.centralserver.thesis.domain.mongo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "server_stats")
public class ProcessData implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    @JsonProperty("status")
    private String status;
    @JsonProperty("cpu_usage")
    private double cpuUsage;
    @JsonProperty("memory_usage")
    private double memoryUsage;
    @JsonProperty("pid")
    private int pid;
    @JsonProperty("create_time")
    private double createTime;
    @JsonProperty("name")
    private String name;
    @JsonProperty("server_id")
    private String serverId;
    @JsonProperty("timestamp")
    private String timestamp;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ProcessData() {
    }

    /**
     * 
     * @param createTime
     * @param memoryUsage
     * @param status
     * @param name
     * @param cpuUsage
     * @param pid
     * @param serverId
     */
    public ProcessData(String status, double cpuUsage, double memoryUsage,
                       int pid, double createTime, String name, String serverId,
                       String timestamp) {
        super();
        this.status = status;
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;
        this.pid = pid;
        this.createTime = createTime;
        this.name = name;
        this.serverId = serverId;
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public double getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(double memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public double getCreateTime() {
        return createTime;
    }

    public void setCreateTime(double createTime) {
        this.createTime = createTime;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    
    
    

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(status).append(cpuUsage).append(memoryUsage).append(pid).append(createTime).append(name).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ProcessData) == false) {
            return false;
        }
        ProcessData rhs = ((ProcessData) other);
        return new EqualsBuilder().append(status, rhs.status)
                .append(cpuUsage, rhs.cpuUsage)
                .append(memoryUsage, rhs.memoryUsage)
                .append(pid, rhs.pid).append(createTime, rhs.createTime)
                .append(name, rhs.name).append(serverId, rhs.serverId)
                .append(timestamp, rhs.timestamp)
                .isEquals();
    }

}
