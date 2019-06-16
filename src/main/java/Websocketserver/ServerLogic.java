package Websocketserver;

import Game.Game;
import Game.Player;
import REST.RESTCommunicator;
import REST.RESTMsg;
import REST.RESTMsgType;
import Socketcomm.Communicator;
import Socketcomm.MsgType;
import Socketcomm.SocketMsg;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javafx.scene.paint.Color;
import netscape.javascript.JSObject;

import javax.websocket.Session;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class ServerLogic implements IObserver{

    //Singleton
    private static ServerLogic serverLogic = new ServerLogic();
    private ServerLogic(){}
    public  static ServerLogic getInstance(){
        return serverLogic;
    }

    public Game game;

    private String resterror = "";

    public ArrayList<Player> getPlayers() {
        return players;
    }
    private Gson g = new Gson();
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Connection> connections = new ArrayList<>();

    private void addConnection(Connection connection){ connections.add(connection);}

    private ArrayList<Connection> getConnections() {
        return connections;
    }

    void newConnection(Session session) {
        Connection conn = new Connection(session,Connectiontype.UNDEF);
        addConnection(conn);
    }

    private Connection getConnection(Session session){
        for(Connection c: connections){
            if(c.session == session){
                return c; }
        }return null;
    }

    private void addPlayer(String name, Connection c, int playernr) throws IOException, InterruptedException {
        Player player = new Player(name,c,playernr);
        players.add(player);
        getConnection(c.session).connectiontype = Connectiontype.GAME;
        player.setConnection(getConnection(c.session));
        checkStartgame();
    }

    private void checkStartgame() throws IOException, InterruptedException {
        if(players.size() == 2){
            Thread.sleep(2000);
            startGame();
        }

    }

    private void startGame() throws IOException {
        this.game = new Game(players,this,1);
        SocketMsg socketMsg = new SocketMsg(MsgType.GAMESTART);
        socketMsg.playernr = game.randomStart(players).getPlayernr();
        System.out.println("Game started, player "+socketMsg.playernr+ " starts.");
        sendAll(socketMsg);
    }

    private int decidePlayernr(){ return this.players.size()+1;
    }

    void removeConnection(Session session){
        connections.removeIf(connection -> connection.session == session);
    }

    private void sendMsg(SocketMsg socketMsg, Session session) throws IOException,ArrayIndexOutOfBoundsException {
        System.out.println("sending"+g.toJson(socketMsg, SocketMsg.class));
        session.getBasicRemote().sendText(g.toJson(socketMsg, SocketMsg.class));
    }

    void handleMsg(String msg, Session session) throws IOException, InterruptedException {
        System.out.println(msg);

        SocketMsg socketMsg = g.fromJson(msg,SocketMsg.class);

        switch(socketMsg.msgType){
            case LOGIN:
                if(authLogin(socketMsg.user,socketMsg.pass)){
                getConnection(session).connectiontype = Connectiontype.GAME;
                socketMsg.msgType = MsgType.LOGINSUCCES;
                socketMsg.playernr = decidePlayernr();
                sendMsg(socketMsg,session);
                addPlayer(socketMsg.user,getConnection(session),decidePlayernr());
                break;
            }else{
                    socketMsg.msgType = MsgType.LOGINFAIL;
                    socketMsg.msg = resterror;
                    sendMsg(socketMsg,session);
            break;}
            case REGISTER:
                if(authRegister(socketMsg.user,socketMsg.pass)){
                    socketMsg.msgType = MsgType.REGSUCCES;
                    sendMsg(socketMsg,session);
                    break;
                }else{socketMsg.msgType = MsgType.REGFAIL;
                    socketMsg.msg = resterror;
                    sendMsg(socketMsg,session);
                    break;}
            case MOVE:
                System.out.println("point: "+socketMsg.p);
                try {
                    game.playCoin(getPlayer(socketMsg.playernr),socketMsg.p.x);
                } catch (Exception e) {
                    throw new RuntimeException(e);}
                socketMsg.p.y = game.getLastPlayed().getLocation().y - 1;

                socketMsg.c = game.decideColor(getPlayer(socketMsg.playernr));
                sendAll(socketMsg);
                break;
        }
    }

    private void sendAll(SocketMsg socketMsg) throws IOException {
        for(Connection c: getConnections()){
            if(c.connectiontype == Connectiontype.GAME){
                sendMsg(socketMsg,c.session);
            }
        }
    }

    private Player getPlayer(int playernr){
        for(Player player: players){
            if(player.getPlayernr() == playernr){
                return player;
            }
        }
        System.out.println("Error: No player found.(Serverlogic.GetPlayer()");
        return null;
    }
    private boolean authLogin(String user, String pass){
        RESTCommunicator rcom = new RESTCommunicator();
        RESTMsg restMsg = new RESTMsg(RESTMsgType.LOGIN);
        restMsg.setLogin(user,pass);
        final RESTMsg restAnswer = rcom.postRegister(restMsg);
        if(restAnswer.getRestMsgType() == RESTMsgType.LOGINSUCCES){
            System.out.println("true");
            return true;}
        else{
            resterror = restAnswer.msg;
            System.out.println("false");
            return false;
        }
    }

    private boolean authRegister(String user,String pass){
        RESTCommunicator rcom = new RESTCommunicator();
        RESTMsg restMsg = new RESTMsg(RESTMsgType.REGISTER);
        restMsg.setLogin(user,pass);
        final RESTMsg restAnswer = rcom.postRegister(restMsg);
        if(restAnswer.getRestMsgType() == RESTMsgType.REGSUCCES){
            System.out.println("true");
            return true;}
        else{
            resterror = restAnswer.msg;
            System.out.println("false");
            return false;
        }


    }

    @Override
    public void update(Object o) throws IOException {
        if(o instanceof Game){
            SocketMsg socketMsg = new SocketMsg(MsgType.WIN);
            socketMsg.playernr = ((Game) o).lastPlayed.player.getPlayernr();
            sendAll(socketMsg);
        }
    }
}
