/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.web.response;

import java.util.List;

/**
 *
 * @author killer
 */
public class AjaxFormResponse {

    private String status;
    private List errorMessageList;
    private Object payload;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List getErrorMessageList() {
        return this.errorMessageList;
    }

    public void setErrorMessageList(List errorMessageList) {
        this.errorMessageList = errorMessageList;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
    
}
