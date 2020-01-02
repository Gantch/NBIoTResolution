package com.gantch.nbiot.model;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class DeviceRelation {

    private String id;

    private String mac;

    private String name;

    private String nickName;

    private Integer tenantId;

    private Integer customerId;

    private String deviceType;

    private String model;

    private String deviceGroupId;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String district;

    private String location;

    private Timestamp createTime;

    public DeviceRelation(String id, String mac, String name, String nickName, Integer tenantId, Integer customerId, String deviceType, String model, String deviceGroupId, BigDecimal latitude, BigDecimal longitude, String district, String location, Timestamp createTime) {
        this.id = id;
        this.mac = mac;
        this.name = name;
        this.nickName = nickName;
        this.tenantId = tenantId;
        this.customerId = customerId;
        this.deviceType = deviceType;
        this.model = model;
        this.deviceGroupId = deviceGroupId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.district = district;
        this.location = location;
        this.createTime = createTime;
    }
}
