package Websocketserver;

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
        if(msg.equals("Hoi")){
            System.out.println("handled");
            for(Connection c: connections){
                c.session.getBasicRemote().sendText("Hallo");

            }
            session.getBasicRemote().sendText("Hallo");
            System.out.println("hond");
        }

    }


}
