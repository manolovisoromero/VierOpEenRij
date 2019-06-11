package Game;

import Database.DatabaseAccess;
import Socketcomm.Communicator;
import Socketcomm.MsgHandler;
import Socketcomm.MsgType;
import Socketcomm.SocketMsg;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class App extends Application {


    private static final int tilesize = 80;
    private static final int columns = 7;
    private static final int rows = 6;
    private Stage stage;
    public GameController gameController;
    Random random = new Random();
    DatabaseAccess dal = new DatabaseAccess();

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    private ArrayList<Button> buttons = new ArrayList<>();

    public void setPlayernr(int playernr) {
        this.playernr = playernr;
        Platform.runLater(() -> stage.setTitle("Player "+playernr));
    }

    public int getPlayernr() {
        return playernr;
    }

    private int playernr;

    public GridPane getGridPane() {
        return gridPane;
    }

    public GridPane gridPane;
    //public Circle [][] circles = new 2Circle [6][7];

    private GridPane gridPane(){
        GridPane gridpane = new GridPane();
        for (int y = 1; y < rows+1; y++) {
            for (int x = 0; x < columns; x++) {

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
                    buttons.add(button);
                    button.setOnAction(e -> {
                        try {
                            gameController.buttonHandler(new Point(finalX,finalY));
                        } catch (IOException | InterruptedException ex) {
                            ex.printStackTrace();
                        }
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
    public void start(Stage stage) throws IOException, InterruptedException {

        stage.setScene(new Scene(fieldmaker()));
        stage.show();
        this.stage = stage;
        this.gameController = new GameController(this);
        MsgHandler.getInstance().setGameController(this.gameController);
        Communicator.getInstance().start();
        SocketMsg socketMsg = new SocketMsg(MsgType.LOGIN);
        socketMsg.setLogin("Manolo","12345");
        Communicator.getInstance().sendMsg(new Gson().toJson(socketMsg),Communicator.getInstance().session);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
