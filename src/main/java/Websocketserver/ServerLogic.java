package Websocketserver;

import Game.Player;
import REST.RESTCommunicator;
import REST.RESTMsg;
import REST.RESTMsgType;
import Socketcomm.Communicator;
import Socketcomm.MsgType;
import Socketcomm.SocketMsg;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import netscape.javascript.JSObject;

import javax.websocket.Session;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ServerLogic {

    //Singleton
    private static ServerLogic serverLogic = new ServerLogic();
    private ServerLogic(){}
    public  static ServerLogic getInstance(){
        return serverLogic;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Gson g = new Gson();


    private ArrayList<Player> players = new ArrayList<>();



    private ArrayList<Connection> connections = new ArrayList<>();

    public void addConnection(Connection connection){
        connections.add(connection);
    }

    public ArrayList<Connection> getConnections() {
        return connections;
    }


    public void newConnection(Session session) throws IOException {
        Connection conn = new Connection(session,Connectiontype.UNDEF);
        addConnection(conn);
        returnPlayernr(session);
        //Player player = new Player()
    }

    public Connection getConnection(Session session){
        for(Connection c: connections){
            if(c.session == session){
                return c;
            }
        }
        return null;

    }

    public void returnPlayernr(Session session) throws IOException {
        //sendMsg("9"+decidePlayernr()+"0",session);
    }

    public int decidePlayernr(){
        return this.connections.size();
    }

    public void removeConnection(Session session){
        Iterator<Connection> iter = connections.iterator();
        while(iter.hasNext()){
            if(iter.next().session == session){
                iter.remove();
            }
        }
    }


    public void sendMsg(SocketMsg socketMsg, Session session) throws IOException {
        session.getBasicRemote().sendText(g.toJson(socketMsg, SocketMsg.class));
    }

    public void handleMsg(String msg, Session session) throws IOException {
//        if(msg.equals("Hoi")){
//            for(Connection c: connections){
//                sendMsg("Hallo",c.session);
//                //c.session.getBasicRemote().sendText("Hallo");
//
//            }
//        }
//        if(msg.equals("Communication open")){
//            for(Connection c: connections){
//                sendMsg("1",c.session);
//            }
//        }
//        if(msg.equals("client")) {
//            Thread t1 = new Thread(new Runnable() {
//                public void run() {
//                    try {
//                        for (int i = 0; i < 8; i++) {
//                            for (int j = 0; j < 6; j++) {
//                                Thread.sleep(100);
//                                sendMsg("1"+i+j,session);
//                                //session.getBasicRemote().sendText("1"+i+j);
//                            }
//                        }
//                    } catch (InterruptedException | IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//            t1.start();

        SocketMsg socketMsg = g.fromJson(msg,SocketMsg.class);

        if(authLogin(socketMsg.user,socketMsg.pass)){
            getConnection(session).connectiontype = Connectiontype.GAME;
            socketMsg.msgType = MsgType.LOGINSUCCES;
            sendMsg(socketMsg,session);

        }
    }
    public boolean authLogin(String user, String pass){
        RESTCommunicator rcom = new RESTCommunicator();
        RESTMsg restMsg = new RESTMsg(RESTMsgType.LOGIN);
        restMsg.setLogin(user,pass);
        if(rcom.postRegister(restMsg).getRestMsgType() == RESTMsgType.LOGINSUCCES){
            System.out.println("true");
            return true;}
        else{
            System.out.println("false");
            return false;
        }
    }
}
