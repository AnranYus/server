package com.inxtes.nowsyncserver.web.socket;

import com.inxtes.nowsyncserver.exception.NotHaveUUIDException;
import com.inxtes.nowsyncserver.model.Device;
import com.inxtes.nowsyncserver.service.DeviceService;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@Component
public class SessionPool {
    private static final HashMap<String, Session> POOL = new HashMap<>();
    static
    DeviceService deviceService;

    public SessionPool(DeviceService deviceService) {
        SessionPool.deviceService = deviceService;
    }

    public static void inPool(Session session, String uuid) throws NotHaveUUIDException {

        if (uuid == null) {
            //Throw
            throw new NotHaveUUIDException();
        }

        POOL.put(uuid, session);//入池

        Device device = deviceService.getDeviceByUUID(uuid);//检查设备库
        if (device == null) {//无 则入库
            Device newDevice = new Device();
            newDevice.setUuid(uuid);
            newDevice.setOnline(true);
            newDevice.setLastUpdate(new Date());
            deviceService.addDevice(newDevice);
        } else
            deviceService.switchOnlineStatus(true, uuid);//更改在线状态


    }

    public static void close(String uuid) {
        POOL.remove(uuid);//出池
        deviceService.switchOnlineStatus(false, uuid);//切换在线状态
    }

    public static Session get(String uuid) {
        return POOL.get(uuid);
    }

    public static void notifyUpdateByUUID(String uuid) {
        try {
            get(uuid).getBasicRemote().sendText("UPDATE");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void notifyAllUpDate(String uuid) {
        for (String key : SessionPool.POOL.keySet()) {
            if (!Objects.equals(key, uuid)) {
                try {
                    get(uuid).getBasicRemote().sendText("UPDATE");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public String getUUIDBySession(Session session) {
        for (String key : POOL.keySet()) {
            if (Objects.equals(POOL.get(key), session)) {
                return key;
            }
        }
        return null;
    }

}
