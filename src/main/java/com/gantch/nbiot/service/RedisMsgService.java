package com.gantch.nbiot.service;

public interface RedisMsgService {

    boolean saveCycleAlarmMessage(String mac,String timeStamp);

    Integer verifyCycleAlarmMessage(String mac);
}
