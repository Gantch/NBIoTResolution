package com.gantch.nbiot.dao;

import com.gantch.nbiot.model.NbiotDevice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * Created by rongshuai on 2019/9/11
 */
@Mapper
public interface NBIoTDeviceMapper {
    @Insert("INSERT INTO nbiot_device(mac,name,deviceId,deviceType,model,createTime) " +
            "VALUES (#{mac},#{name}, #{deviceId},#{deviceType},#{model},#{createTime})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int addNbiotDevice(NbiotDevice nbiotDevice);

//    @Select("SELECT auto_increment FROM information_schema.tables WHERE table_schema='BUPT_IOT' and table_name='nbiotdevice'")
//    int getNbiotDeviceNumber();

    @Select("SELECT * FROM nbiot_device where mac = #{mac}")
    Integer getNbiotDeviceBymac(String mac);

    @Select("SELECT * FROM nbiot_device where mac = #{mac}")
    NbiotDevice getNbiotDevice(String mac);

}
