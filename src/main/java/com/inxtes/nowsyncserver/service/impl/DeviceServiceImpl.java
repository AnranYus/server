package com.inxtes.nowsyncserver.service.impl;

import com.inxtes.nowsyncserver.mapper.DeviceMapper;
import com.inxtes.nowsyncserver.model.Device;
import com.inxtes.nowsyncserver.service.DeviceService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DeviceServiceImpl implements DeviceService {
    final
    DeviceMapper deviceMapper;

    public DeviceServiceImpl(DeviceMapper deviceMapper) {
        this.deviceMapper = deviceMapper;
    }

    @Override
    public Integer addDevice(Device device) {
        return deviceMapper.insertDevice(device);
    }

    @Override
    public Device getDeviceByUUID(String uuid) {
        return deviceMapper.selectDeviceByUUID(uuid);
    }

    @Override
    public Integer setUpdateTime(Date date, String uuid) {
        return deviceMapper.upDateLastUpdateTime(date, uuid);
    }

    @Override
    public Integer switchOnlineStatus(Boolean isOnline, String uuid) {
        return deviceMapper.upDateOnlineStatus(isOnline, uuid);
    }
}
