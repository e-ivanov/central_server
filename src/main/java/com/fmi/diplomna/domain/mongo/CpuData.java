
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
    "usage",
    "num_of_cores"
})
public class CpuData {

    @JsonProperty("usage")
    private double usage;
    @JsonProperty("num_of_cores")
    private int numOfCores;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CpuData() {
    }

    /**
     * 
     * @param numOfCores
     * @param usage
     */
    public CpuData(double usage, int numOfCores) {
        super();
        this.usage = usage;
        this.numOfCores = numOfCores;
    }

    @JsonProperty("usage")
    public double getUsage() {
        return usage;
    }

    @JsonProperty("usage")
    public void setUsage(double usage) {
        this.usage = usage;
    }

    @JsonProperty("num_of_cores")
    public int getNumOfCores() {
        return numOfCores;
    }

    @JsonProperty("num_of_cores")
    public void setNumOfCores(int numOfCores) {
        this.numOfCores = numOfCores;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(usage).append(numOfCores).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CpuData) == false) {
            return false;
        }
        CpuData rhs = ((CpuData) other);
        return new EqualsBuilder().append(usage, rhs.usage).append(numOfCores, rhs.numOfCores).isEquals();
    }

}
