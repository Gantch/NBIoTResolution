package com.gantch.nbiot.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyvmsapi.model.v20170525.SingleCallByTtsRequest;
import com.aliyuncs.dyvmsapi.model.v20170525.SingleCallByTtsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.gantch.nbiot.model.DeviceMessage;
import com.gantch.nbiot.model.DeviceRelation;
import com.gantch.nbiot.model.NbiotAlarmLog;
import com.gantch.nbiot.model.NbiotDevice;
import com.google.gson.Gson;
import org.apache.commons.codec.binary.Hex;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * Created by lcw332 on 2019/12/21
 */

public class DataService {

      public void resolution(byte[] validData,int length,String mac,NbiotDeviceService nbiotDeviceService,DeviceRelationDao relationDao,NbiotAlarmLogDao nbiotAlarmLogDao,DeviceMessageDao deviceMessageDao,RedisMsgService msgService){
          String deviceType = Hex.encodeHexString(new byte[]{validData[0]},false);
          int infoLength = validData[1];
          System.out.println(deviceType);
          NbiotDevice device = new NbiotDevice();
          device.setMac(mac);
          device.setName(deviceType2Type(deviceType));
          device.setDeviceType(deviceType);
          device.setDeviceId(UUID.randomUUID().toString());
          device.setModel("BC28");
          Timestamp createTime = new Timestamp(System.currentTimeMillis());
          device.setCreateTime(createTime);
          if (nbiotDeviceService.getNbiotDeviceByMac(mac)<1){//检查设备创建情况，如未创建就在nbiot_device中创建
              nbiotDeviceService.addNbiotDevice(device);
          }
          switch (deviceType){
              case "20":
                  Boolean isSmoking;
                  String status = "normal";
                  if (validData[2] == (byte)0x01){//报警
                      isSmoking = true;
                      DeviceRelation relation= relationDao.getDeviceRelation(device.getMac());
                      if (relation !=null){//如果已有绑定关系
                          DeviceRelation relatio2= relationDao.getDeviceRelation(device.getMac());
                          System.out.println(relatio2);
                          System.out.println("mac为"+relation.getMac());
                          System.out.println("设备已有绑定关系");
                          String deviceId = relation.getId();
                          String deviceName = relation.getName();
                          String deviceNickName = relation.getNickName();
                          Integer tenantId =relation.getTenantId();
                          System.out.println(relation);
                          BigDecimal latitude = relation.getLatitude();
                          BigDecimal longitude = relation.getLongitude();
                          String district=relation.getDistrict();
                          String location = relation.getLocation();
                          String alarmType="烟雾报警";
                          System.out.println("mac为"+relation.getMac());
                          System.out.println("当前设备的名字为:" + deviceName);
                          System.out.println("当前设备昵称为:"+ deviceNickName);
                          System.out.println("当前设备的Id为:" + deviceId);
                          System.out.println("当前设备的纬度为:" + latitude);
                          System.out.println("当前设备的经度为:" + longitude);
                          System.out.println("当前设备的详细地址为:"+district);
                          System.out.println("当前时间戳为:" + createTime.toString());
                          NbiotAlarmLog nbiotAlarmLog = new NbiotAlarmLog(device.getDeviceId(),createTime,deviceName,latitude,longitude,alarmType,tenantId);
                          System.out.println(nbiotAlarmLogDao.addNbiotAlarmLog(nbiotAlarmLog));
                             try{
                                  List<DeviceMessage> deviceMessages = deviceMessageDao.getDeviceMessageById(deviceId);
                                  System.out.println(deviceMessages.toString());
                                  for (DeviceMessage deviceMessage : deviceMessages){
//                                        String phoneNumber = deviceMessage.getPhoneNumber();
                                        String phoneNumber = "13558695773";
                                    if (deviceMessage.getStatus().equals(1)){//普通报警
                                        if (deviceNickName == null || deviceNickName.length()==0 ||deviceName.equals("")){
                                            System.out.println("出现报警，向：" + phoneNumber + "发送短信");
                                            System.out.println(deviceName +"," + location +"," + alarmType +"," + phoneNumber);
                                            sendMs(deviceName,location,alarmType,phoneNumber);//向对应手机号发送报警短信
                                            sendVoice(deviceName,phoneNumber);
                                        }else{
                                            System.out.println("出现报警，向：" + phoneNumber + "发送短信");
                                            System.out.println(deviceNickName +"," + location +"," + alarmType +"," + phoneNumber);
                                            sendMs(deviceNickName,location,alarmType,phoneNumber);//向对应手机号发送报警短信
                                            sendVoice(deviceNickName,phoneNumber);
                                        }
                                    }
                                    if (deviceMessage.getStatus().equals(3)){//周期性报警 10分钟为一周期
                                        System.out.println("出现报警为周期性报警：10分钟为一周期");
                                        if (msgService.verifyCycleAlarmMessage(mac)==0){
//                                            sendMs(deviceName,location,alarmType,phoneNumber);
                                            sendVoice(deviceName,phoneNumber);
                                            Timestamp timestamp =new Timestamp(System.currentTimeMillis());
                                            String time=timestamp.toString();
                                            msgService.saveCycleAlarmMessage(mac,time);
                                        }
                                    }
                                  }
                              }catch (Exception e){
                                  System.out.println("当前尚无设备对应的报警手机号"+e);
                              }
                          }else{
    //                          没有绑定关系
                        System.out.println("设备没有绑定关系");
                        NbiotDevice nbiotDevice = nbiotDeviceService.getNbiotDevice(mac);
                        String deviceName = nbiotDevice.getName();
                        String deviceId = nbiotDevice.getDeviceId();
                        BigDecimal latitude = null;//没有绑定关系 经纬度为空
                        BigDecimal longitude = null;
                        String alarmType = "烟雾报警";
                        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                        System.out.println("当前设备的名字为:" + deviceName);
                        System.out.println("当前设备的Id为:" + deviceId);
                        System.out.println("当前设备的纬度为:" + latitude);
                        System.out.println("当前设备的经度为:" + longitude);
                        System.out.println("当前时间戳为:" + currentTime.toString());
                        System.out.println("烟雾报警");
                        NbiotAlarmLog nbiotAlarmLog = new NbiotAlarmLog(deviceId,currentTime,deviceName,latitude,longitude,alarmType,null);
                        nbiotAlarmLogDao.addNbiotAlarmLog(nbiotAlarmLog);
                          }
                      }else{ isSmoking = false; }
                      if(isSmoking){
                          if(validData[3] == (byte)0x00){
                              status = "normal";
                          }else{
                              status = "low";
                          }
                      }
                break;
              default:
                System.out.println("无匹配设备");
                break;
          }

      }

   public static String deviceType2Type(String deviceId){
        String type = null;
        switch (deviceId){
            case "20":
                type = "smoke_detector";
                break;
            default:
                type = "unknown";
                break;
        }
        return type;
    }

    public void sendMs(String deviceName,String location,String alarmType,String phoneNumber){//发送短信报警通知
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FgntVB75X2BoJQR5qUr", "UIlRGb6N2eX1boNTuFxMhQoYKQEzhz");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", "云消防");
        request.putQueryParameter("TemplateCode", "SMS_181505951");
        request.putQueryParameter("TemplateParam", "{\"deviceName\":\"" +deviceName+ "\", \"location\":\"" + location + "\" ,\"alarmType\":\"" + alarmType + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    public void sendVoice(String deviceName,String phoneNumber) throws ClientException {//发送语音报警通知
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FgntVB75X2BoJQR5qUr", "UIlRGb6N2eX1boNTuFxMhQoYKQEzhz");
        profile.addEndpoint("cn-hangzhou", "cn-hangzhou","Dyvmsapi","dyvmsapi.aliyuncs.com");
        IAcsClient client = new DefaultAcsClient(profile);

        SingleCallByTtsRequest request = new SingleCallByTtsRequest();
        request.setRegionId("cn-hangzhou");
        request.setCalledShowNumber("02566823407");
        request.setCalledNumber(phoneNumber);
        request.setTtsCode("TTS_176936831");
        request.setTtsParam("{\"name\":\"" + deviceName + "\"}");
        request.setPlayTimes(2);
        request.setVolume(200);

        try {
            SingleCallByTtsResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }

    }
}
