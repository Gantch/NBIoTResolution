package com.gantch.nbiot.service;

import com.gantch.nbiot.dao.DeviceMessageMapper;
import com.gantch.nbiot.model.DeviceMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rongshuai on 2019/11/5 21:36
 */
@Service
public class DeviceMessageDao {
    @Autowired
    private DeviceMessageMapper deviceMessageMapper;




    public List<DeviceMessage> getDeviceMessageById(String deviceId){//根据设备的id查找设备对应的所有报警电话
        return deviceMessageMapper.getDeviceMessageById(deviceId);
    }








}
