package com.gantch.nbiot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by rongshuai on 2019/11/5 21:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceMessage {
    private Integer id;
    private String deviceId;
    private Integer customerId;
    private String phoneNumber;
    private Integer status;


    public DeviceMessage(Integer id,String deviceId, String phoneNumber, Integer status){
        this.id = id;
        this.deviceId = deviceId;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public DeviceMessage(String deviceId, String phoneNumber, Integer status) {
        this.deviceId = deviceId;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}