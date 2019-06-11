package Game;

import Game.App;
import Socketcomm.Communicator;
import Socketcomm.MsgType;
import Socketcomm.SocketMsg;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
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
        socketMsg.playernr = app.getPlayernr();
        communicator.sendMsg(gson.toJson(socketMsg,SocketMsg.class),communicator.session);
    }

    public void circleFiller(Point p, Color c){
        getCircle(p.x,p.y).setFill(c);
    }

    public void switchButtons(boolean Switch){
        for(Button button: app.getButtons()){
            button.setDisable(Switch);
        }
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

    public void showWin(int playernr){
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setTitle("Player "+ app.getPlayernr());
        dialogStage.setWidth(200);
        dialogStage.setHeight(200);

        Button button = new Button("Ok");
        button.setOnAction(actionEvent -> {
            dialogStage.close();
        });
        VBox vbox = new VBox(new Text("Player "+ playernr + " wins!"), button);
        vbox.setAlignment(Pos.CENTER);

        dialogStage.setScene(new Scene(vbox));
        dialogStage.show();
    }
}
