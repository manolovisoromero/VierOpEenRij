package websocketServer;

import game.Game;
import game.Player;
import rest.RESTMsg;
import rest.RESTMsgType;
import socketComm.MsgType;
import socketComm.SocketMsg;
import com.google.gson.Gson;

import javax.websocket.Session;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Logger;

public class ServerLogic implements IObserver{

    private Logger logger = Logger.getLogger(this.getClass().getName());


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
        Objects.requireNonNull(getConnection(c.session)).connectiontype = Connectiontype.GAME;

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
        logger.info("game has begun, starting player: "+socketMsg.playernr);
        sendAll(socketMsg);
    }

    private int decidePlayernr(){ return this.players.size()+1;
    }

    void removeConnection(Session session){
        connections.removeIf(connection -> connection.session == session);
    }

    private void sendMsg(SocketMsg socketMsg, Session session) throws IOException,ArrayIndexOutOfBoundsException {
    logger.info("Sending: "+ socketMsg.msgType);
    session.getBasicRemote().sendText(g.toJson(socketMsg, SocketMsg.class));
    }

    void handleMsg(String msg, Session session) throws IOException, InterruptedException {
        System.out.println(msg);

        SocketMsg socketMsg = g.fromJson(msg,SocketMsg.class);

        switch(socketMsg.msgType){
            case LOGIN:
                if(authLogin(socketMsg.user,socketMsg.pass)){
                Objects.requireNonNull(getConnection(session)).connectiontype = Connectiontype.GAME;
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
                if(game.playCoin(getPlayer(socketMsg.playernr),socketMsg.p.x)){
                socketMsg.p.y = game.getLastPlayed().getLocation().y - 1;
                socketMsg.c = game.decideColor(getPlayer(socketMsg.playernr));
                sendAll(socketMsg);
                }
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
        return null;
    }
    private boolean authLogin(String user, String pass){
        RESTCommunicator rcom = new RESTCommunicator();
        RESTMsg restMsg = new RESTMsg(RESTMsgType.LOGIN);
        restMsg.setLogin(user,pass);
        final RESTMsg restAnswer = rcom.postRegister(restMsg);
        if(restAnswer.getRestMsgType() == RESTMsgType.LOGINSUCCES){
            return true;}
        else{
            resterror = restAnswer.msg;
            return false;
        }
    }

    private boolean authRegister(String user,String pass){
        RESTCommunicator rcom = new RESTCommunicator();
        RESTMsg restMsg = new RESTMsg(RESTMsgType.REGISTER);
        restMsg.setLogin(user,pass);
        final RESTMsg restAnswer = rcom.postRegister(restMsg);
        if(restAnswer.getRestMsgType() == RESTMsgType.REGSUCCES){
            return true;}
        else{
            resterror = restAnswer.msg;
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
