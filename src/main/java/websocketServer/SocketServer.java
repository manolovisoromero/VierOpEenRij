package websocketServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.glassfish.tyrus.server.Server;

public class SocketServer {

    public static void main(String[] args){
        runServer();
    }

    public static void runServer(){
        org.glassfish.tyrus.server.Server server = new org.glassfish.tyrus.server.Server("localhost",8025,"/websockets",ServerEndpoint.class);

        try {
            server.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please press a key to stop the server");
            reader.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            server.stop();
        }


    }
}
