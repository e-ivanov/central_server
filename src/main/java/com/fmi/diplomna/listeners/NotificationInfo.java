/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.listeners;

import org.joda.time.DateTime;

/**
 *
 * @author killer
 */
public class NotificationInfo {

    private volatile DateTime latestCpuNotification = null;
    private volatile DateTime latestMemoryNotification = null;
    private volatile DateTime lastestDiskNotification = null;
    private volatile DateTime latestStatisticsReceived = null;
    private volatile DateTime latestExecutedCpuCheck = null;
    private volatile DateTime lastestExecutedMemoryCheck = null;
    private volatile DateTime lastestExecutedDiskUsageCheck = null;
    
    /**
     * @return the latestCpuNotification
     */
    public DateTime getLatestCpuNotification() {
        return latestCpuNotification;
    }

    /**
     * @param latestCpuNotification the latestCpuNotification to set
     */
    public void setLatestCpuNotification(DateTime latestCpuNotification) {
        this.latestCpuNotification = latestCpuNotification;
    }

    /**
     * @return the latestMemoryNotification
     */
    public DateTime getLatestMemoryNotification() {
        return latestMemoryNotification;
    }

    /**
     * @param latestMemoryNotification the latestMemoryNotification to set
     */
    public void setLatestMemoryNotification(DateTime latestMemoryNotification) {
        this.latestMemoryNotification = latestMemoryNotification;
    }

    /**
     * @return the lastestDiskNotification
     */
    public DateTime getLastestDiskNotification() {
        return lastestDiskNotification;
    }

    /**
     * @param lastestDiskNotification the lastestDiskNotification to set
     */
    public void setLastestDiskNotification(DateTime lastestDiskNotification) {
        this.lastestDiskNotification = lastestDiskNotification;
    }

    /**
     * @return the latestStatisticsReceived
     */
    public DateTime getLatestStatisticsReceived() {
        return latestStatisticsReceived;
    }

    /**
     * @param latestStatisticsReceived the latestStatisticsReceived to set
     */
    public void setLatestStatisticsReceived(DateTime latestStatisticsReceived) {
        this.latestStatisticsReceived = latestStatisticsReceived;
    }

    /**
     * @return the latestExecutedCpuCheck
     */
    public DateTime getLatestExecutedCpuCheck() {
        return latestExecutedCpuCheck;
    }

    /**
     * @param latestExecutedCpuCheck the latestExecutedCpuCheck to set
     */
    public void setLatestExecutedCpuCheck(DateTime latestExecutedCpuCheck) {
        this.latestExecutedCpuCheck = latestExecutedCpuCheck;
    }

    /**
     * @return the lastestExecutedMemoryCheck
     */
    public DateTime getLastestExecutedMemoryCheck() {
        return lastestExecutedMemoryCheck;
    }

    /**
     * @param lastestExecutedMemoryCheck the lastestExecutedMemoryCheck to set
     */
    public void setLastestExecutedMemoryCheck(DateTime lastestExecutedMemoryCheck) {
        this.lastestExecutedMemoryCheck = lastestExecutedMemoryCheck;
    }

    /**
     * @return the lastestExecutedDiskUsageCheck
     */
    public DateTime getLastestExecutedDiskUsageCheck() {
        return lastestExecutedDiskUsageCheck;
    }

    /**
     * @param lastestExecutedDiskUsageCheck the lastestExecutedDiskUsageCheck to set
     */
    public void setLastestExecutedDiskUsageCheck(DateTime lastestExecutedDiskUsageCheck) {
        this.lastestExecutedDiskUsageCheck = lastestExecutedDiskUsageCheck;
    }
    
    
    
}
