package Game;

import java.util.ArrayList;
import java.awt.*;


public class Field {

    public Coin[][] getField() {
        return field;
    }

    Game game;

    private Coin [][] field = new Coin[6][7];
    private ArrayList<Coin> coins = new ArrayList<Coin>();

    public Field(Game game){
        this.game = game;
    }

    public void addCoin(Coin c) {
        Point p = c.getLocation();
        field[p.x][p.y] = c;
    }










}
