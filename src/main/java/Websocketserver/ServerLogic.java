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


    public void newConnection(Session session){
        Connection conn = new Connection(session,Connectiontype.UNDEF);
        addConnection(conn);
    }

    public void removeConnection(Session session){
        Iterator<Connection> iter = connections.iterator();
        while(iter.hasNext()){
            if(iter.next().session == session){
                iter.remove();
            }
        }
    }

    public void handleMsg(String msg, Session session) throws IOException {
        System.out.println("hoiii");
        if(msg.equals("Hoi")){
            System.out.println("handled1");
            for(Connection c: connections){
                c.session.getBasicRemote().sendText("Hallo");

            }
            session.getBasicRemote().sendText("Hallo");
            System.out.println("hond1");
        }
        if(msg.equals("Communication open")){
            System.out.println("handled");
            for(Connection c: connections){
                c.session.getBasicRemote().sendText("1");
            }
        }

        Thread t1 = new Thread(new Runnable() {
            public void run()
            {
                while(true){
                    try {
                        for (int i = 5; i < 8; i++) {
                            for (int j = 1; j < 9; j++) {
                                session.getBasicRemote().sendText("1"+i+j);
                            }

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }});
        t1.start();

    }


}
