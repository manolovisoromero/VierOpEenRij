package Socketcomm;


import Game.App;
import Game.GameController;
import javafx.application.Platform;
import com.google.gson.Gson;



import javax.websocket.Session;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class MsgHandler implements IMsgHandler{

    Session session;

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public GameController gameController;

    public Gson gson = new Gson();

    private static MsgHandler handler = new MsgHandler();

    public static MsgHandler getInstance(){
        return handler;
    }

    Communicator communicator = new Communicator();

    public MsgHandler(){

    }



    public void handle(String msg, Session session) throws InterruptedException {
        SocketMsg socketMsg = gson.fromJson(msg,SocketMsg.class);
        System.out.println(socketMsg.msgType);


        switch(socketMsg.msgType){
            case LOGINSUCCES:
                break;
            case LOGIN:
                break;
            case MOVE:
                System.out.println("hallo?");
                gameController.circleFiller(socketMsg.p,socketMsg.c);
                break;
            case LOGINFAIL:
                break;
            case SPECTATE:
                break;
        }

        if(socketMsg.msgType == MsgType.LOGINSUCCES){
            System.out.println("Login success");
        }
    }

    public void handle9(){

    }

    public void sendMsg(String msg,Session session) throws IOException, InterruptedException {
        communicator.sendMsg(msg,session);
    }

    public void open(Session session){
        this.session = session;
    }
}
