
package com.eivanov.centralserver.thesis.domain.mongo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "total",
    "used",
    "usage_percent",
    "free"
})
public class MemoryData {

    @JsonProperty("total")
    private long total;
    @JsonProperty("used")
    private long used;
    @JsonProperty("usage_percent")
    private double usagePercent;
    @JsonProperty("free")
    private long free;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MemoryData() {
    }

    /**
     * 
     * @param usagePercent
     * @param total
     * @param free
     * @param used
     */
    public MemoryData(long total, long used, double usagePercent, long free) {
        super();
        this.total = total;
        this.used = used;
        this.usagePercent = usagePercent;
        this.free = free;
    }

    @JsonProperty("total")
    public long getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(long total) {
        this.total = total;
    }

    @JsonProperty("used")
    public long getUsed() {
        return used;
    }

    @JsonProperty("used")
    public void setUsed(long used) {
        this.used = used;
    }

    @JsonProperty("usage_percent")
    public double getUsagePercent() {
        return usagePercent;
    }

    @JsonProperty("usage_percent")
    public void setUsagePercent(double usagePercent) {
        this.usagePercent = usagePercent;
    }

    @JsonProperty("free")
    public long getFree() {
        return free;
    }

    @JsonProperty("free")
    public void setFree(long free) {
        this.free = free;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(total).append(used).append(usagePercent).append(free).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MemoryData) == false) {
            return false;
        }
        MemoryData rhs = ((MemoryData) other);
        return new EqualsBuilder().append(total, rhs.total).append(used, rhs.used).append(usagePercent, rhs.usagePercent).append(free, rhs.free).isEquals();
    }

}
