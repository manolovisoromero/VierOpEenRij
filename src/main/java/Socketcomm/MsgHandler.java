package Socketcomm;


import javax.websocket.Session;
import java.io.IOException;

public class MsgHandler {

    Session session;

    private static MsgHandler handler = new MsgHandler();

    public static MsgHandler getInstance(){
        return handler;
    }

    public MsgHandler(){

    }

    public void handle(String msg, Session session) throws IOException, InterruptedException {
        sendMsg(msg,session);
    }

    public void sendMsg(String msg,Session session) throws IOException, InterruptedException {
        Communicator.getInstance().send(msg,session);
    }

    public void open(Session session){
        this.session = session;

    }
}
