package client;

import org.glassfish.tyrus.client.ClientManager;
import socketComm.ClientEndpoint;

import javax.websocket.DeploymentException;
import javax.websocket.Session;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Communicator implements ICommunicator {

    public Session session;
    public Client app;

    public Session getSesh(){
        return this.session;
    }

    private static Communicator comm = new Communicator();

    public static Communicator getInstance(){
        return comm;
    }

    public Communicator(){

    }



    public void start() throws IOException {
        ClientManager client = ClientManager.createClient();
        this.session = null;
        try {
            this.session = client.connectToServer(ClientEndpoint.class, new URI("ws://localhost:8025/websockets/game"));
        } catch (DeploymentException | URISyntaxException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();

        }
    }





    public void sendMsg(String msg,Session session) {
        try {
            session.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();

        }
    }

}
