package com.gantch.nbiot.model;

import lombok.Data;

/**
 * Created by rongshuai on 2019/11/5 21:34
 */
@Data
public class DeviceMessage {
    private Integer id;
    private String deviceId;
    private Integer customerId;
    private String phoneNumber;
    private Integer status;

    public DeviceMessage(Integer id, String deviceId, Integer customerId, String phoneNumber, Integer status) {
        this.id = id;
        this.deviceId = deviceId;
        this.customerId = customerId;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }
}