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



    public void handle(String msg, Session session){
        SocketMsg socketMsg = gson.fromJson(msg,SocketMsg.class);
        System.out.println(socketMsg.msgType);


        switch(socketMsg.msgType){
            case LOGINSUCCES:
                gameController.app.setPlayernr(socketMsg.playernr);
                System.out.println("Login success");
                break;
            case LOGIN:
                break;
            case MOVE:
                gameController.circleFiller(socketMsg.p,socketMsg.c);
                switchTurn(socketMsg.playernr);
                break;
            case LOGINFAIL:
                break;
            case SPECTATE:
                break;
            case GAMESTART:
                startTurn(socketMsg.playernr);
                break;
            case WIN:
                Platform.runLater(() -> gameController.showWin(socketMsg.playernr));
                break;
    }}

    public void switchTurn(int playernr){
        boolean Switch = false;
        if(playernr == gameController.app.getPlayernr()){
            Switch = true;
        }
        gameController.switchButtons(Switch);
    }

    public void startTurn(int playernr){
        if(gameController.app.getPlayernr() != playernr){
            gameController.switchButtons(true);

        }
    }




    public void sendMsg(String msg,Session session) throws IOException, InterruptedException {
        communicator.sendMsg(msg,session);
    }

}
