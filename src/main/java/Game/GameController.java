package Game;

import Game.App;
import Socketcomm.Communicator;
import Socketcomm.MsgType;
import Socketcomm.SocketMsg;
import com.google.gson.Gson;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.awt.Point;
import java.io.IOException;

public class GameController {



    public App app;
    public Communicator communicator = Communicator.getInstance();
    public Gson gson = new Gson();

    public GameController(App app){
        this.app = app;
    }

    public void changeName(){
        app.getStage().setTitle("Hallo");
    }

    public void buttonHandler(Point p ) throws IOException, InterruptedException {
        SocketMsg socketMsg = new SocketMsg(MsgType.MOVE);
        socketMsg.p = p;
        communicator.sendMsg(gson.toJson(socketMsg,SocketMsg.class),communicator.session);
    }

    public void circleFiller(Point p, Color c){
        getCircle(p.x,p.y).setFill(c);

    }
    
    public Circle getCircle(int x, int y){
        for(Node node : app.getGridPane().getChildren()){
            if(node instanceof Pane){
                if(GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node)==y+1){
                    for(Node node1 : ((Pane) node).getChildren()){
                        if(node1 instanceof Circle ){
                            return ((Circle) node1);
                        }}
                }
            }
        }
        return null;
    }
}
