
package com.fmi.diplomna.domain.mongo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "perc_of_used_disk",
    "total_disk_space",
    "free_disk_space",
    "used_disk_space"
})
public class DiskData {

    @JsonProperty("perc_of_used_disk")
    private double percOfUsedDisk;
    @JsonProperty("total_disk_space")
    private long totalDiskSpace;
    @JsonProperty("free_disk_space")
    private long freeDiskSpace;
    @JsonProperty("used_disk_space")
    private long usedDiskSpace;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DiskData() {
    }

    /**
     * 
     * @param percOfUsedDisk
     * @param usedDiskSpace
     * @param freeDiskSpace
     * @param totalDiskSpace
     */
    public DiskData(double percOfUsedDisk, long totalDiskSpace, long freeDiskSpace, long usedDiskSpace) {
        super();
        this.percOfUsedDisk = percOfUsedDisk;
        this.totalDiskSpace = totalDiskSpace;
        this.freeDiskSpace = freeDiskSpace;
        this.usedDiskSpace = usedDiskSpace;
    }

    @JsonProperty("perc_of_used_disk")
    public double getPercOfUsedDisk() {
        return percOfUsedDisk;
    }

    @JsonProperty("perc_of_used_disk")
    public void setPercOfUsedDisk(double percOfUsedDisk) {
        this.percOfUsedDisk = percOfUsedDisk;
    }

    @JsonProperty("total_disk_space")
    public long getTotalDiskSpace() {
        return totalDiskSpace;
    }

    @JsonProperty("total_disk_space")
    public void setTotalDiskSpace(long totalDiskSpace) {
        this.totalDiskSpace = totalDiskSpace;
    }

    @JsonProperty("free_disk_space")
    public long getFreeDiskSpace() {
        return freeDiskSpace;
    }

    @JsonProperty("free_disk_space")
    public void setFreeDiskSpace(long freeDiskSpace) {
        this.freeDiskSpace = freeDiskSpace;
    }

    @JsonProperty("used_disk_space")
    public long getUsedDiskSpace() {
        return usedDiskSpace;
    }

    @JsonProperty("used_disk_space")
    public void setUsedDiskSpace(long usedDiskSpace) {
        this.usedDiskSpace = usedDiskSpace;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(percOfUsedDisk).append(totalDiskSpace).append(freeDiskSpace).append(usedDiskSpace).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DiskData) == false) {
            return false;
        }
        DiskData rhs = ((DiskData) other);
        return new EqualsBuilder().append(percOfUsedDisk, rhs.percOfUsedDisk).append(totalDiskSpace, rhs.totalDiskSpace).append(freeDiskSpace, rhs.freeDiskSpace).append(usedDiskSpace, rhs.usedDiskSpace).isEquals();
    }

}
