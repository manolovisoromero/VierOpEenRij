package Socketcomm;

import javax.websocket.Session;
import java.io.IOException;

public interface ICommunicator {

    void sendMsg(String msg, Session session) throws IOException, InterruptedException ;
}
