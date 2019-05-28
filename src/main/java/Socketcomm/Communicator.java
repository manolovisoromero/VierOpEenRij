package Socketcomm;

import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.DeploymentException;
import javax.websocket.Session;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Communicator {

    public Session session;

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
        } catch (DeploymentException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        this.session.getBasicRemote().sendText("client");
    }

    public void send(String msg,Session session) throws IOException, InterruptedException {
        session.getBasicRemote().sendText(msg);
    }
}
