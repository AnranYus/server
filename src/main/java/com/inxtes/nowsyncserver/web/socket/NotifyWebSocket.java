package com.inxtes.nowsyncserver.web.socket;

import com.inxtes.nowsyncserver.exception.NotHaveUUIDException;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;

@ServerEndpoint("/socket/notify/{uuid}")
@Component
public class NotifyWebSocket {

    @OnOpen
    public void onOpen(Session session, @PathParam("uuid") String uuid) {
        try {
            SessionPool.inPool(session, uuid);
        } catch (NotHaveUUIDException e) {
            try {
                session.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }


        Date deviceUpdateTime = SessionPool.getLastUpdateTime(uuid);
        Date lastUpdateTime = DatabaseHandler.getLastUpdateTime();

        //比较同步时间
        if (deviceUpdateTime.before(lastUpdateTime)) {
            SessionPool.notifyUpdateByUUID(uuid);
        }


    }

    @OnClose
    public void onClose(@PathParam("uuid") String uuid) {
        SessionPool.close(uuid);
    }

    @OnMessage
    public void onMessage(String message, @PathParam("uuid") String uuid) {
        if (message.equals("COMPLETE")) {
            if (uuid != null) {
                SessionPool.setLastUpdateTime(uuid, new Date());
            }
        }
    }


}
