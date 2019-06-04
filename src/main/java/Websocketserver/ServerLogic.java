package Websocketserver;

import Game.Player;
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


    public void sendMsg(String msg, Session session) throws IOException {
        SocketMsg socketMsg = new SocketMsg(MsgType.MOVE);
        socketMsg.p = new Point(2,2);
        socketMsg.session = session;
        Gson g = new Gson();
        System.out.println(socketMsg.p);
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

        if(msg.equals("client")) {
            sendMsg("hoi",session);
        }

    }


}
