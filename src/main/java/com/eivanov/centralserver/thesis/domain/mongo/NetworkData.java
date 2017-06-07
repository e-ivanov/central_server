
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
    "packets_sent",
    "bytes_sent",
    "packets_recv",
    "bytes_recv"
})
public class NetworkData {

    @JsonProperty("packets_sent")
    private long packetsSent;
    @JsonProperty("bytes_sent")
    private long bytesSent;
    @JsonProperty("packets_recv")
    private long packetsRecv;
    @JsonProperty("bytes_recv")
    private long bytesRecv;

    /**
     * No args constructor for use in serialization
     * 
     */
    public NetworkData() {
    }

    /**
     * 
     * @param bytesRecv
     * @param bytesSent
     * @param packetsSent
     * @param packetsRecv
     */
    public NetworkData(long packetsSent, long bytesSent, long packetsRecv, long bytesRecv) {
        super();
        this.packetsSent = packetsSent;
        this.bytesSent = bytesSent;
        this.packetsRecv = packetsRecv;
        this.bytesRecv = bytesRecv;
    }

    @JsonProperty("packets_sent")
    public long getPacketsSent() {
        return packetsSent;
    }

    @JsonProperty("packets_sent")
    public void setPacketsSent(long packetsSent) {
        this.packetsSent = packetsSent;
    }

    @JsonProperty("bytes_sent")
    public long getBytesSent() {
        return bytesSent;
    }

    @JsonProperty("bytes_sent")
    public void setBytesSent(long bytesSent) {
        this.bytesSent = bytesSent;
    }

    @JsonProperty("packets_recv")
    public long getPacketsRecv() {
        return packetsRecv;
    }

    @JsonProperty("packets_recv")
    public void setPacketsRecv(long packetsRecv) {
        this.packetsRecv = packetsRecv;
    }

    @JsonProperty("bytes_recv")
    public long getBytesRecv() {
        return bytesRecv;
    }

    @JsonProperty("bytes_recv")
    public void setBytesRecv(long bytesRecv) {
        this.bytesRecv = bytesRecv;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(packetsSent).append(bytesSent).append(packetsRecv).append(bytesRecv).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof NetworkData) == false) {
            return false;
        }
        NetworkData rhs = ((NetworkData) other);
        return new EqualsBuilder().append(packetsSent, rhs.packetsSent).append(bytesSent, rhs.bytesSent).append(packetsRecv, rhs.packetsRecv).append(bytesRecv, rhs.bytesRecv).isEquals();
    }

}
