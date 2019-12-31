package com.gantch.nbiot.model;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by rongshuai on 2019/11/20 20:53
 */
@Data
public class NbiotAlarmLog {
    private Integer id;
    private String deviceId;
    private Timestamp timestamp;
    private String name;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String alarmType;
    private Integer tenantId;

    public NbiotAlarmLog(String deviceId, Timestamp createTime, String deviceName, BigDecimal latitude, BigDecimal longitude, String alarmType, Integer tenantId) {
        this.deviceId=deviceId;
        this.timestamp=createTime;
        this.name=deviceName;
        this.latitude=latitude;
        this.longitude=longitude;
        this.alarmType=alarmType;
        this.tenantId=tenantId;
    }

    public NbiotAlarmLog(Integer id, String deviceId, Timestamp timestamp, String name, BigDecimal latitude, BigDecimal longitude, String alarmType, Integer tenantId) {
        this.id = id;
        this.deviceId = deviceId;
        this.timestamp = timestamp;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.alarmType = alarmType;
        this.tenantId = tenantId;
    }
}
