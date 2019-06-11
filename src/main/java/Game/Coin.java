package Game;


import java.awt.Point;
import javafx.scene.paint.Color;


public class Coin {

    public Point location;
    private Color color;
    public Player player;

    public Coin(Player player, Color color, Point p){
        this.player = player;
        this.color = color;
        this.location = p;
    }

    public Point getLocation(){
        return this.location;
    }

}
