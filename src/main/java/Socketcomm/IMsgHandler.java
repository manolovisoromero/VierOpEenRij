package Socketcomm;

import javax.websocket.Session;
import java.io.IOException;

public interface IMsgHandler {


    //Scans the incoming msg and decides how to handle it;
    void handle(String msg, Session session) throws IOException, InterruptedException;

    //If handle() decides a message needs to be sent, this method will forward the msg and session to the communicator;
    void sendMsg(String msg, Session session) throws IOException, InterruptedException;
}
