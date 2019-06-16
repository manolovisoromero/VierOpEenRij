package game;

import websocketServer.Connection;

public class Player {

    private String name;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private Connection connection;

    public void setPlayernr(int playernr) {
        this.playernr = playernr;
    }

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
