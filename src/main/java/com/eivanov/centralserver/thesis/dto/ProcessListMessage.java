
package com.eivanov.centralserver.thesis.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status",
    "cpu_usage",
    "memory_usage",
    "pid",
    "create_time",
    "name"
})
public class ProcessListMessage {

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

    /**
     * No args constructor for use in serialization
     * 
     */
    public ProcessListMessage() {
    }

    /**
     * 
     * @param createTime
     * @param memoryUsage
     * @param status
     * @param name
     * @param cpuUsage
     * @param pid
     */
    public ProcessListMessage(String status, double cpuUsage, double memoryUsage, int pid, double createTime, String name) {
        super();
        this.status = status;
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;
        this.pid = pid;
        this.createTime = createTime;
        this.name = name;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("cpu_usage")
    public double getCpuUsage() {
        return cpuUsage;
    }

    @JsonProperty("cpu_usage")
    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    @JsonProperty("memory_usage")
    public double getMemoryUsage() {
        return memoryUsage;
    }

    @JsonProperty("memory_usage")
    public void setMemoryUsage(double memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    @JsonProperty("pid")
    public int getPid() {
        return pid;
    }

    @JsonProperty("pid")
    public void setPid(int pid) {
        this.pid = pid;
    }

    @JsonProperty("create_time")
    public double getCreateTime() {
        return createTime;
    }

    @JsonProperty("create_time")
    public void setCreateTime(double createTime) {
        this.createTime = createTime;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
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
        if ((other instanceof ProcessListMessage) == false) {
            return false;
        }
        ProcessListMessage rhs = ((ProcessListMessage) other);
        return new EqualsBuilder().append(status, rhs.status).append(cpuUsage, rhs.cpuUsage).append(memoryUsage, rhs.memoryUsage).append(pid, rhs.pid).append(createTime, rhs.createTime).append(name, rhs.name).isEquals();
    }

}
