/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.scheduler;

import com.fmi.diplomna.domain.services.CheckType;
import com.fmi.diplomna.domain.services.NotificationFactory;
import com.fmi.diplomna.dto.NotificationDTO;
import com.fmi.diplomna.dto.NotificationType;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.mongodb.core.WriteResultChecking.LOG;

/**
 *
 * @author killer
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class PingJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(PingJob.class);
    private String url;
    private int port = -1;
    private String path;
    private String protocol;
    private boolean isDown = false;
    private int hearthbeatInterval = -1;
    private int unresponsiveInterval = -1;
    private int secondsBetweenNotifications;

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        JobDataMap dataMap = jec.getJobDetail().getJobDataMap();
        DateTime now = (DateTime) dataMap.get("last_successful_ping");
        try {
            final String targetUrl = assembleUrl();
            final URLConnection connection = new URL(targetUrl).openConnection();
            logger.info("Trying to connect to: " + targetUrl);
            connection.connect();
            logger.info("Service " + targetUrl + " available, yeah!");
            dataMap.put("is_down", false);
            dataMap.put("last_successful_ping", DateTime.now());
            dataMap.remove("last_unsuccessful_ping");
            dataMap.remove("last_unavailable_notification");
        } catch (final MalformedURLException e) {
            logger.error("Malformed URL! " + e);
            throw new IllegalStateException("Bad URL: " + url, e);
        } catch (final IOException e) {
            logger.info("Service " + url + " unavailable, oh no!", e);
            dataMap.put("is_down", true);
            //This will be called for the first time when the service is unresponsive
            if (dataMap.get("last_unsuccessful_ping") == null) {
                dataMap.put("last_unsuccessful_ping", DateTime.now());
                return;
            }
            
            if(canSendNotification(dataMap)){
//                NotificationDTO dto = NotificationFactory.buildNotification(server, NotificationType.CRITICAL, CheckType.AVAILABILITY)
            }else{
                dataMap.put("last_unavailable_notification", DateTime.now());
                dataMap.put("last_unsuccessful_ping", DateTime.now());
                logger.info("Notification was send");
            }

            logger.info("Job was called!");
        }

    }

    private String assembleUrl() {
        String url = getProtocol() + "://" + getUrl();
        if (getPort() != -1) {
            url += ":" + getPort();
        }
        if (getPath() != null) {
            url += "/" + getPath();
        }
        logger.info("The assembled path is: " + url);
        return url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isIsDown() {
        return isDown;
    }

    public void setIsDown(boolean isDown) {
        this.isDown = isDown;
    }

    public int getHearthbeatInterval() {
        return hearthbeatInterval;
    }

    public void setHearthbeatInterval(int hearthbeatInterval) {
        this.hearthbeatInterval = hearthbeatInterval;
    }

    public int getUnresponsiveInterval() {
        return unresponsiveInterval;
    }

    public void setUnresponsiveInterval(int unresponsiveInterval) {
        this.unresponsiveInterval = unresponsiveInterval;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getSecondsBetweenNotifications() {
        return secondsBetweenNotifications;
    }

    public void setSecondsBetweenNotifications(int secondsBetweenNotifications) {
        this.secondsBetweenNotifications = secondsBetweenNotifications;
    }

    private boolean canSendNotification(JobDataMap dataMap) {

        DateTime lastUnsuccessfulPing = (DateTime) dataMap.get("last_unsuccessful_ping");
        Seconds secDiff = Seconds.secondsBetween(lastUnsuccessfulPing, DateTime.now());
        logger.info("The main diff in seconds is: " + secDiff.getSeconds());
        logger.info("The unresponsive interval is: " + getUnresponsiveInterval());
        if (secDiff.getSeconds() >= getUnresponsiveInterval()) {
            DateTime lastNotification = (DateTime) dataMap.get("last_unavailable_notification");
            if (lastNotification != null) {
                Seconds seconds = Seconds.secondsBetween(lastNotification, DateTime.now());
                logger.info("The diff in seconds is: " + seconds.getSeconds());
                logger.info("The seconds between notifications is: " + getSecondsBetweenNotifications());
                if (seconds.getSeconds() >= getSecondsBetweenNotifications()) {
                    //Send notification
                    dataMap.put("last_unavailable_notification", DateTime.now());
                    dataMap.put("last_unsuccessful_ping", DateTime.now());
                    return true;
                } else {
                    logger.info("It is too early to send notification");
                    return false;
                }
            }else{
                return false;
            } 
        }else{
            return false;
        }

    }

    private String assembleNotificatonContent(){
        StringBuilder builder = new StringBuilder();
        builder.append("Недостъпен сървъс с адрес: ");
        builder.append(getUrl());
        return builder.toString();
    }
    
    private void submitNotification() {

    }
}
