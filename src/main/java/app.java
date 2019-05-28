import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;



public class app extends Application {


    private static final int tilesize = 80;
    private static final int columns = 7;
    private static final int rows = 6;

    private GridPane gridPane(){
        GridPane gridpane = new GridPane();
        for (int y = 1; y < rows+1; y++) {
            for (int x = 0; x < columns+1; x++) {
                if(y==1){
                    Button button = new Button();
                    button.setText("Button"+x);
                    button.setPadding(new Insets(20));
                    gridpane.add(button,x,y-1);
                }
                Pane pane = new Pane();
                pane.setPrefSize(100,100);
                Circle circle = new Circle(tilesize /2);
                circle.setCenterX(tilesize/2);
                circle.setCenterY(tilesize/2);
                circle.setFill(Color.WHITE);
                pane.getChildren().add(circle);
                pane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                gridpane.add(pane,x,y);
            }

        }
        return gridpane;
    }

    private Parent fieldmaker(){
        Pane root = new Pane();
        root.getChildren().add(gridPane());
        return root;
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(fieldmaker()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
