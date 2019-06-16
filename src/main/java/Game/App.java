package Game;

import Database.DatabaseAccess;
import Socketcomm.Communicator;
import Socketcomm.MsgHandler;
import Socketcomm.MsgType;
import Socketcomm.SocketMsg;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class App extends Application {






    @Override
    public void start(Stage stage) throws IOException {
        Communicator.getInstance().start();

        StageManager.getInstance().startStage().show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
