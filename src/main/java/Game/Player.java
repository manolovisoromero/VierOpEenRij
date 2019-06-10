package Game;

import Websocketserver.Connection;

import javax.websocket.Session;
import java.util.ArrayList;

public class Player {

    private String name;
    private Connection connection;
    private int playernr;
    private boolean win;

    public Player(String name,Connection conn, int playernr){
        this.name = name;
        this.connection = conn;
        this.playernr = playernr;
    }
}
