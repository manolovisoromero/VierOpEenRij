package Game;

import Socketcomm.Communicator;
import Socketcomm.MsgHandler;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application {


    private static final int tilesize = 80;
    private static final int columns = 7;
    private static final int rows = 6;
    public int hallo = 0;
    private Stage stage;
    public GameController gameController;

    public GridPane getGridPane() {
        return gridPane;
    }

    public GridPane gridPane;
    //public Circle [][] circles = new 2Circle [6][7];

    private GridPane gridPane(){
        GridPane gridpane = new GridPane();
        for (int y = 1; y < rows+1; y++) {
            for (int x = 0; x < columns+1; x++) {

                Pane pane = new Pane();
                pane.setPrefSize(100,100);
                Circle circle = new Circle(tilesize /2);
                circle.setCenterX(tilesize/2);
                circle.setCenterY(tilesize/2);
                circle.setFill(Color.WHITE);
                pane.getChildren().add(circle);
                pane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                gridpane.add(pane,x,y);
                if(y==1){
                    Button button = new Button();
                    button.setText("Button"+x);
                    button.setPadding(new Insets(20));
                    gridpane.add(button,x,y-1);
                    int finalX = x;
                    int finalY = y;
                    button.setOnAction(e -> {
                        gameController.buttonHandler(finalX, finalY -1);
                    });
                }
            }
        }
        return gridpane;
    }

    private Parent fieldmaker(){
        Pane root = new Pane();
        this.gridPane = gridPane();
        root.getChildren().add(this.gridPane);
        return root;
    }

    public Stage getStage(){
        return this.stage;
    }

    @Override
    public void start(Stage stage) throws IOException {

        stage.setScene(new Scene(fieldmaker()));
        stage.show();
        this.stage = stage;
        this.gameController = new GameController(this);
        MsgHandler.getInstance().setGameController(this.gameController);
        System.out.println(this.gameController);
        Communicator.getInstance().start();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
