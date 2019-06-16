package socketComm;


import client.Communicator;
import client.GameController;
import client.LoginController;
import client.StageManager;
import javafx.application.Platform;
import com.google.gson.Gson;



import javax.websocket.Session;
import java.io.IOException;

public class MsgHandler implements IMsgHandler{


    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    private GameController gameController;

    public void setLoginController(LoginController loginController) { this.loginController = loginController; }

    private LoginController loginController;

    public Gson gson = new Gson();

    private static MsgHandler handler = new MsgHandler();

    public static MsgHandler getInstance(){
        return handler;
    }

    private Communicator communicator = new Communicator();

    public MsgHandler(){

    }



    public void handle(String msg, Session session){
        SocketMsg socketMsg = gson.fromJson(msg,SocketMsg.class);
        System.out.println(socketMsg.msgType);
        System.out.print(gson.toJson(socketMsg, SocketMsg.class));


        switch(socketMsg.msgType){
            case LOGINSUCCES:
                System.out.println(gameController);
                loginController.startGameWindow();
                StageManager.getInstance().setPlayernr(socketMsg.playernr);
                break;
            case LOGIN:
                break;
            case MOVE:
                gameController.circleFiller(socketMsg.p,socketMsg.c);
                switchTurn(socketMsg.playernr);
                break;
            case LOGINFAIL:
                Platform.runLater(() -> loginController.logError(socketMsg.msg));
                break;
            case SPECTATE:
                break;
            case GAMESTART:
                startTurn(socketMsg.playernr);
                break;
            case REGISTER:
                break;
            case REGSUCCES:
                break;
            case REGFAIL:
                Platform.runLater(() -> loginController.regError(socketMsg.msg));
                break;
            case WIN:
                Platform.runLater(() -> gameController.showWin(socketMsg.playernr));
                break;
    }}

    private void switchTurn(int playernr){
        boolean Switch = false;
        if(playernr == gameController.stageManager.getPlayernr()){
            Switch = true;
        }
        gameController.switchButtons(Switch);
    }

    private void startTurn(int playernr){
        if(gameController.stageManager.getPlayernr() != playernr){
            gameController.switchButtons(true);

        }
    }




    public void sendMsg(String msg,Session session) throws IOException, InterruptedException {
        communicator.sendMsg(msg,session);
    }

}
