/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.hibernate;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author killer
 */
@Embeddable
public class Measure{
    
    
    @Column(name = "cpu_warn_level", nullable = false)
    private float warnLevel;
    
    @Column(name = "cpu_critical_level", nullable = false)
    private float criticalLevel;
    
    @Column(name = "cpu_notification_inverval", nullable = false)
    private int notificationInverval;

    public float getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(float warnLevel) {
        this.warnLevel = warnLevel;
    }

    public float getCriticalLevel() {
        return criticalLevel;
    }

    public void setCriticalLevel(float criticalLevel) {
        this.criticalLevel = criticalLevel;
    }

    public int getNotificationInverval() {
        return notificationInverval;
    }

    public void setNotificationInverval(int notificationInverval) {
        this.notificationInverval = notificationInverval;
    }


    
    
    
}
