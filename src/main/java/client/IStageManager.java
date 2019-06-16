package client;

import javafx.stage.Stage;

import java.io.IOException;

public interface IStageManager {


    //Return start stage / opening screen
    Stage startStage();

    //Returm game stage / game screen
    Stage gameStage() throws IOException, InterruptedException;
}
