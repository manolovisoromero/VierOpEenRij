package Game;

import Game.App;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GameController {



    public App app;

    public GameController(App app){
        this.app = app;
    }

    public void changeName(){
        app.getStage().setTitle("Hallo");
    }

    public void buttonHandler(int x, int y){
        for(Node node : app.getGridPane().getChildren()){
            if(node instanceof Pane){
                if(GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node)==y+1){
                    for(Node node1 : ((Pane) node).getChildren()){
                    if(node1 instanceof Circle ){
                        ((Circle) node1).setFill(Color.RED);
                    }}
                }


            }

        }
    }
}
