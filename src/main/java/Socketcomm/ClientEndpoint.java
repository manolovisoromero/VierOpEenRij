package Socketcomm;

import java.io.IOException;
import java.util.logging.Logger;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@javax.websocket.ClientEndpoint
public class ClientEndpoint {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @OnOpen
    public void onOpen(Session session) throws IOException, InterruptedException {
        logger.info("Connected... " + session.getId());
        MsgHandler.getInstance().sendMsg("Communication open",session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException {
        logger.info("Received...."+ message);
        //WebsocketComm.getInstance().send(message,session);
        MsgHandler.getInstance().handle(message,session);
    }


    @OnClose
    public void onClose(Session session, CloseReason closeReason){
        logger.info(String.format("Session %s close because of %s", session.getId(), closeReason));
    }
}
