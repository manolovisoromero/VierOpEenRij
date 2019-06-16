package Game;

import Socketcomm.Communicator;
import Socketcomm.MsgHandler;
import Socketcomm.MsgType;
import Socketcomm.SocketMsg;
import com.google.gson.Gson;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

public class StageManager implements  IStageManager{

    //Singleton

    private static StageManager manager = new StageManager();

    public static StageManager getInstance(){
        return manager;
    }

    private StageManager(){
    }


    //The stages
    private Stage StartStage;

    private TextField user = new javafx.scene.control.TextField("Manolo");
    private TextField pass = new javafx.scene.control.TextField("12345");
    private Label userlabel = new javafx.scene.control.Label("Username: ");
    private Label passlabel = new Label("Password: ");
    Label loginerror = new Label("Login unsuccesful.");
    private Button login = new Button("Login");
    private Button register = new Button("Register");


    private Stage GameStage;

    //GameStage numbers
    private static final int tilesize = 80;
    private static final int columns = 7;
    private static final int rows = 6;



    //Controllers
    private GameController gameController;
    private LoginController loginController;

    //Client info
    private int playernr;
    private GridPane gamegrid;
    private ArrayList<Button> gamebuttons = new ArrayList<>();


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
                    button.setText("Click to drop here");
                    button.setPadding(new Insets(20));
                    gridpane.add(button,x,y-1);
                    int finalX = x;
                    int finalY = y;
                    gamebuttons.add(button);
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
        gamegrid = gridpane;
        return gridpane;
    }

    private Parent fieldmaker(){
        Pane root = new Pane();
        root.getChildren().add(gridPane());
        return root;
    }


    @SuppressWarnings("Duplicates")
    public Stage startStage(){
        loginController = new LoginController();
        MsgHandler.getInstance().setLoginController(loginController);
        Pane root = new Pane();
        GridPane gridPane = new GridPane();
        login.setOnAction(actionEvent -> {
            try {
                loginController.login(user.getText(),pass.getText());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        register.setOnAction(actionEvent -> {
            try {
                loginController.register(user.getText(),pass.getText());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        gridPane.add(userlabel,0,0);
        gridPane.add(passlabel,0,1);
        gridPane.add(user,1,0);
        gridPane.add(pass,1,1);
        gridPane.add(login,2,0);
        gridPane.add(loginerror,3,0);
        gridPane.add(register,2,1);
        loginerror.setVisible(false);
        root.getChildren().add(gridPane);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        this.StartStage = stage;
        return stage;
    }





    public Stage gameStage() {
        setGameController(new GameController());
        MsgHandler.getInstance().setGameController(gameController);
        this.GameStage = new Stage();
        this.GameStage.setScene(new Scene(fieldmaker()));
        this.GameStage.setTitle(String.valueOf(getPlayernr()));
        return this.GameStage;
    }

    GridPane getGamegrid() { return gamegrid; }
    Stage getStartStage() { return StartStage; }
    public int getPlayernr() { return playernr; }
    public void setPlayernr(int playernr) { this.playernr = playernr; }
    Stage getGameStage() { return GameStage; }
    private void setGameController(GameController gameController) { this.gameController = gameController; }
    ArrayList<Button> getGamebuttons() { return gamebuttons; }
}
