package Websocketserver;

import javax.websocket.Session;

public class Connection {

    public Connection(Session session, Connectiontype connectiontype) {
        this.session = session;
        this.connectiontype = connectiontype;
    }

    Session session;
    Connectiontype connectiontype;
}


