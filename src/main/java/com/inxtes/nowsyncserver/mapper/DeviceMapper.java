package com.inxtes.nowsyncserver.mapper;

import com.inxtes.nowsyncserver.model.Device;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface DeviceMapper {

    Device selectDeviceByUUID(String uuid);

    Integer insertDevice(Device device);

    Integer upDateLastUpdateTime(@Param("date") Date date, @Param("uuid") String uuid);

    Integer upDateOnlineStatus(@Param("isOnline") Boolean isOnline, @Param("uuid") String uuid);

    Boolean selectOnlineStatus(String uuid);
}
