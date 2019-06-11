package Game;

import Websocketserver.Connection;

import javax.websocket.Session;
import java.util.ArrayList;

public class Player {

    private String name;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private Connection connection;
    private int playernr;

    public void setWin(boolean win) {
        this.win = win;
    }

    private boolean win = false;

    public Player(String name,Connection conn, int playernr){
        this.name = name;
        this.connection = conn;
        this.playernr = playernr;
    }

    public String getName() {
        return name;
    }

    public int getPlayernr() {
        return playernr;
    }
}
