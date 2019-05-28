import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;



public class app extends Application {


    private static final int tilesize = 80;
    private static final int columns = 7;
    private static final int rows = 6;

    private String opponentName;
    private String opponentName2;

    // Label for opponent's name

    private Label labelOpponentName;
    private Label labelOpponentName2;







    private Shape gridmaker(){
        Shape shape = new Rectangle((columns+1) * tilesize, (rows+1) * tilesize);
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                Circle circle = new Circle(tilesize /2);
                circle.setCenterX(tilesize/2);
                circle.setCenterY(tilesize/2);
                circle.setTranslateX(x*(tilesize+5)+ tilesize/4);
                circle.setTranslateY(y*(tilesize+5)+ tilesize/4);
                shape = Shape.subtract(shape,circle);
            }

        }
        shape.setFill(Color.BLUE);
        return shape;
    }

    private GridPane gridPane(){
        opponentName = "Opponent";
        labelOpponentName = new Label(opponentName + "\'s grid");

        opponentName2 = "Opponent";
        labelOpponentName2 = new Label(opponentName2 + "\'2222");

        Label hallo = new Label("hallo");




        GridPane gridpane = new GridPane();
        gridpane.setHgap((columns+1) * tilesize);
        gridpane.setVgap(tilesize);

        gridpane.add(labelOpponentName,0,0,1,2);
        gridpane.add(labelOpponentName2,0,0,1,1);
        gridpane.add(hallo,2,1,1,1);
        //gridpane.add
        return gridpane;
    }

    private Parent fieldmaker(){
        Pane root = new Pane();

        Shape gridShape = gridmaker();
        root.getChildren().add(gridPane());
        //root.getChildren().add(gridShape);

        return root;
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(fieldmaker()));
        //stage.setResizable(true);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
