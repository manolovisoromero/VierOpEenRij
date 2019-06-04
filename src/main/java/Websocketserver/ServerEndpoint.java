package Websocketserver;

import javax.websocket.*;
import java.io.IOException;
import java.util.logging.Logger;

@javax.websocket.server.ServerEndpoint("/game")
public class ServerEndpoint {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @OnOpen
    public String onOpen(Session session) throws IOException {
        logger.info("Connected...." + session.getId());
        ServerLogic.getInstance().newConnection(session);
        return "open";
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        logger.info("Received...."+ message);
        ServerLogic.getInstance().handleMsg(message, session);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason){
        logger.info("Disconnected...."+ session.getId());
        ServerLogic.getInstance().removeConnection(session);
    }



}
