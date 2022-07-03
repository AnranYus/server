package com.inxtes.nowsyncserver.service;

import com.inxtes.nowsyncserver.model.Device;

import java.util.Date;

public interface DeviceService {
//    Map<String, Device> getAllDevice();

    Integer addDevice(Device device);

    Device getDeviceByUUID(String uuid);

    Integer setUpdateTime(Date date, String uuid);

    Integer switchOnlineStatus(Boolean isOnline, String uuid);
}
