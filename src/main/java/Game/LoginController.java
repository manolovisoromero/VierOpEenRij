package Game;

import Socketcomm.Communicator;
import Socketcomm.MsgType;
import Socketcomm.SocketMsg;
import com.google.gson.Gson;
import javafx.application.Platform;

import java.io.IOException;

public class LoginController {

    private StageManager stageManager = StageManager.getInstance();

    void login(String user, String pass) throws IOException, InterruptedException {
        Thread.sleep(1000);
        SocketMsg socketMsg = new SocketMsg(MsgType.LOGIN);
        socketMsg.setLogin(user,pass);
        Communicator.getInstance().sendMsg(new Gson().toJson(socketMsg),Communicator.getInstance().session);
    }

    void register(String user, String pass) throws IOException, InterruptedException {
        SocketMsg socketMsg = new SocketMsg(MsgType.REGISTER);
        socketMsg.setLogin(user,pass);
        Communicator.getInstance().sendMsg(new Gson().toJson(socketMsg),Communicator.getInstance().session);
    }

    public void startGameWindow(){
        Platform.runLater(() -> stageManager.getStartStage().close());
        Platform.runLater(() -> stageManager.gameStage().show());

    }

    public void regError(String msg){
        stageManager.loginerror.setText(msg);
        stageManager.loginerror.setVisible(true);
    }
    public void logError(String msg){
        stageManager.loginerror.setText(msg);
        stageManager.loginerror.setVisible(true);

    }
}
