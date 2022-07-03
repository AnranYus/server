package com.inxtes.nowsyncserver.web.socket;

import com.inxtes.nowsyncserver.exception.NotHaveUUIDException;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

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
        SessionPool.notifyUpdateByUUID(uuid);

    }

    @OnClose
    public void onClose(@PathParam("uuid") String uuid) {
        SessionPool.close(uuid);
    }
//
//    @OnMessage
//    public void onMessage(String message, Session session) {
//        System.out.println(message);
////        if (message.equals("Update complete")) {
////            String uuid = sessionPool.getUUIDBySession(session);
////            if (uuid != null) {
////                deviceService.setUpdateTime(new Date(), uuid);
////            }
////        }
//    }


}
