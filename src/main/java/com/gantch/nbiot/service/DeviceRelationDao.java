package com.gantch.nbiot.service;

import com.gantch.nbiot.dao.DeviceRelationMapper;
import com.gantch.nbiot.model.DeviceRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceRelationDao {

    @Autowired
    private DeviceRelationMapper DeviceRelationMapper;

    public DeviceRelation getDeviceRelation(String mac){

        return DeviceRelationMapper.selectDeviceRelationByMac(mac) ;
    }


}
