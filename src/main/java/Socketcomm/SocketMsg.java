package Socketcomm;

import javax.websocket.Session;
import java.awt.*;

public class SocketMsg {

    private String msg;
    public Session session;
    public Point p;
    public Color c;
    public MsgType msgType;
    public String user;
    public String pass;


    public SocketMsg(MsgType msgType){
        this.msgType = msgType;
    }
    public void setLogin(String user, String pass){
        this.pass = pass;
        this.user = user;
    }

}
