package Socketcomm;

import javax.websocket.Session;
import java.awt.*;

public class SocketMsg {

    private String msg;
    public Session session;
    public Point p;
    private MsgType msgType;


    public SocketMsg(MsgType msgType){
        this.msgType = msgType;
    }

}
