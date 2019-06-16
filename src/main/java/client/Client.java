package client;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


public class Client extends Application {






    @Override
    public void start(Stage stage) throws IOException {
        Communicator.getInstance().start();

        StageManager.getInstance().startStage().show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
