package Websocketserver;

import Socketcomm.Communicator;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.websocket.Session;
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
    }

    public void returnPlayernr(Session session) throws IOException {
        sendMsg("9"+getConnections().size()+"0",session);

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
        session.getBasicRemote().sendText(msg);
    }

    public void handleMsg(String msg, Session session) throws IOException {
        if(msg.equals("Hoi")){
            for(Connection c: connections){
                sendMsg("Hallo",c.session);
                //c.session.getBasicRemote().sendText("Hallo");

            }
        }
        if(msg.equals("Communication open")){
            for(Connection c: connections){
                sendMsg("1",c.session);
            }
        }
        if(msg.equals("client")) {
            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    try {
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 6; j++) {
                                Thread.sleep(100);
                                sendMsg("1"+i+j,session);
                                //session.getBasicRemote().sendText("1"+i+j);
                            }
                        }
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t1.start();
        }

    }


}
