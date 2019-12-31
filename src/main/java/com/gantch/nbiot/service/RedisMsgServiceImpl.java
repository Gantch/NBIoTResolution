package com.gantch.nbiot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RedisMsgServiceImpl implements RedisMsgService {

    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.deviceMac}")
    private String REDIS_KEY_PREFIX_DEVICE_MAC;
    @Value("${redis.key.expire.deviceMac}")
    private Long CYCLE_EXPIRE_SECONDS;


    public boolean saveCycleAlarmMessage(String mac){//存储周期时间
        redisService.set(REDIS_KEY_PREFIX_DEVICE_MAC+mac,"233");//存储Key-mac Value-authCode?=233
        redisService.expire(REDIS_KEY_PREFIX_DEVICE_MAC+mac,CYCLE_EXPIRE_SECONDS);//到期时间
        return true;
    }

    //对周期报警信息进行校验
    public Integer verifyCycleAlarmMessage(String mac,String authCode){
        String realAuthCode =redisService.get(REDIS_KEY_PREFIX_DEVICE_MAC+mac);
        Boolean result = authCode.equals(realAuthCode);
        if (result==true){
            return 1;//没数据
        }
        return 0;//有数据
    }
}
