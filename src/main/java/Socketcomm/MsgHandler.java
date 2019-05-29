package Socketcomm;


import Game.App;
import Game.GameController;

import javax.websocket.Session;
import java.io.IOException;

public class MsgHandler {

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



    public void handle(String msg, Session session) throws IOException, InterruptedException {
        //sendMsg(msg,session);
        int x = 0;
        int y = 0;
        boolean move = false;
        for (int i=0; i<msg.length(); i++) {
            char c = msg.charAt(i);
            if(i ==0 && Character.toString(c).equals("1")){
                move = true;
            }
            if(i==1){
                x = Integer.parseInt(Character.toString(c));
            }
            if(i==2){
                y = Integer.parseInt(Character.toString(c));
            }
        }
        if(move){
            System.out.println(x+""+y);
            if(this.gameController != null){
                this.gameController.buttonHandler(x,y);
            }

        }
    }

    public void sendMsg(String msg,Session session) throws IOException, InterruptedException {
        communicator.send(msg,session);
    }

    public void open(Session session){
        this.session = session;

    }
}
