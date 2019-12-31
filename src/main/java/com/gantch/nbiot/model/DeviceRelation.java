package com.gantch.nbiot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
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

    @JsonFormat(pattern = "YYYY-MM-ddHH:mm:ss",timezone = "GMT+8")
    private Date createTime;


    public DeviceRelation(String deviceId, String deviceName, String deviceNickName, Integer tenantId, Integer customerId, BigDecimal latitude, BigDecimal longitude, String district, String location) {
        this.id=deviceId;
        this.name=deviceName;
        this.nickName=deviceNickName;
        this.tenantId=tenantId;
        this.customerId=customerId;
        this.latitude=latitude;
        this.longitude=longitude;
        this.district=district;
        this.location=location;
    }
}
