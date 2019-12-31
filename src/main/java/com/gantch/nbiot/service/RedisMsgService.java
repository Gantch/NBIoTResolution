package com.gantch.nbiot.service;

public interface RedisMsgService {

    boolean saveCycleAlarmMessage(String mac);

    Integer verifyCycleAlarmMessage(String mac,String authCode);
}
