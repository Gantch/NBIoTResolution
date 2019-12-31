package com.gantch.nbiot.dao;

import com.gantch.nbiot.model.DeviceRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeviceRelationMapper {


    @Select("SELECT * FROM nbiot_device_relation WHERE mac = #{mac}")
    DeviceRelation selectDeviceRelationByMac(@Param("mac") String mac);

    @Select("SELECT * FROM nbiot_device_relation WHERE deviceId = #{deviceId}")
    List<DeviceRelation> selectDeviceRelationById(@Param("deviceId") String deviceId);
}
