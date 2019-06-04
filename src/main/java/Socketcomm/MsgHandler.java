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


    private static MsgHandler handler = new MsgHandler();

    public static MsgHandler getInstance(){
        return handler;
    }

    Communicator communicator = new Communicator();

    public MsgHandler(){

    }



    public void handle(String msg, Session session) throws InterruptedException {
        int x = 0;
        int y = -1;
        boolean move = false;
        boolean handle9 =  false;
//        for (int i=0; i<msg.length(); i++) {
//            char c = msg.charAt(i);
//            if(i==0 && Character.toString(c).equals("1")){
//                move = true;
//            }
//            if(i==0 && Character.toString(c).equals("9")){
//                handle9 = true;
//            }
//            if(i==1){
//                x = Integer.parseInt(Character.toString(c));
//            }
//            if(i==2){
//                y = Integer.parseInt(Character.toString(c));
//            }
//        }
//        if(move){
//            if(this.gameController != null){
//                this.gameController.buttonHandler(x,y);
//            }
//        }
//        if(handle9){
//            int finalX = x;
//            final GameController game = this.gameController;
//            Platform.runLater(() -> game.app.getStage().setTitle("Player"+ finalX));
//        }
        Gson gson = new Gson();
        SocketMsg socketMsg = gson.fromJson(msg,SocketMsg.class);
        System.out.println(socketMsg.p);
    }

    public void handle9(){

    }

    public void sendMsg(String msg,Session session) throws IOException, InterruptedException {
        communicator.send(msg,session);
    }

    public void open(Session session){
        this.session = session;
    }
}
