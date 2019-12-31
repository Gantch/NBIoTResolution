package com.gantch.nbiot.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by rongshuai on 2019/9/11
 */
@Data
public class NbiotDevice {

//    @ApiModelProperty(value = "ID")
    private Integer id;

//    @ApiModelProperty(value = "设备Mac地址")
    private String mac;

//    @ApiModelProperty(value = "设备名")
    private String name;

//    @ApiModelProperty(value = "设备id")
    private String deviceId;

//    @ApiModelProperty(value = "设备类型")
    private String deviceType;

//    @ApiModelProperty(value = "设备型号")
    private String model;

//    @ApiModelProperty(value = "设备创建时间")
    private Date createTime;

    public String toString(){
        final StringBuilder sb = new StringBuilder("{");
        sb.append(",\"id\":\"")
                .append(id).append("\"");
        sb.append("\"mac\":")
                .append("\""+mac+"\"");
        sb.append(",\"name\":\"")
                .append(name).append("\"");
        sb.append(",\"deviceId\":\"")
                .append(deviceId).append("\"");
        sb.append(",\"deviceType\":\"")
                .append(deviceType).append("\"");
        sb.append(",\"model\":\"")
                .append(model).append("\"");
        sb.append(",\"createTime\":\"")
                .append(createTime).append("\"");
        sb.append('}');
        return sb.toString();
    }
}
