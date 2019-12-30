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


    public boolean saveCycleAlarmMessage(String mac, String timeStamp){//存储周期时间
        redisService.set(REDIS_KEY_PREFIX_DEVICE_MAC+mac,timeStamp);//存储Key-mac Value-timeStamp
        redisService.expire(REDIS_KEY_PREFIX_DEVICE_MAC+mac,CYCLE_EXPIRE_SECONDS);//到期时间
        return true;
    }

    //对周期报警信息进行校验
    public Integer verifyCycleAlarmMessage(String mac){
        String cycle =redisService.get(REDIS_KEY_PREFIX_DEVICE_MAC+mac);
        String time = redisService.get(CYCLE_EXPIRE_SECONDS+mac);
        Boolean result = mac.equals(cycle);
        Boolean check = mac.equals(time);
        if (result){
            return 1;//有数据
        }
        if (check==false){//检查是否有效，过期则删除
            redisService.remove(mac);
        }else{
            return 1;
        }
        return 0;//没数据
    }
}
