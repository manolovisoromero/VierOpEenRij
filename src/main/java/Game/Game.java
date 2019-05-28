package Game;

import java.awt.*;
import java.util.ArrayList;

public class Game {

    public Field getField() {
        return field;
    }

    public boolean win = false;

    private Field field = new Field(this);
    private ArrayList<Player> players;

    public Game(ArrayList<Player> players){
        this.players = players;
    }


    public void playCoin(Player player, int x){
        if(Available(x)) {
            Coin c = new Coin(player, decideColor(player),whereToDrop(x));
            getField().addCoin(c);
        }
    }

    public Color decideColor(Player player){
        if(player == players.get(0)){
            return Color.RED;
        }else{
            return Color.YELLOW;
        }
    }



    public Point whereToDrop(int x){
        boolean bottomhit = false;
        int y = 1;
        Point lastavailable = new Point(x,y);
        while(!bottomhit){
            if(field.getField()[x][y] == null){
                lastavailable.y = y;
                if(y != 6){
                    y++;
                }
            }else{bottomhit = true;}
        }
        return lastavailable;
    }


    public boolean Available(int x){
        if(field.getField()[x][1] == null){
            return true;
        }
        return false;
    }

    public void HorizontalWin(Coin c){
        int j = -3;
        Point p = new Point(c.getLocation());
        for(int i =0;i<4;i++){
            if(!HoriOutOfBounds(p.x,i )&& notNull(c,j,i)){
                if(field.getField()[p.x+j+i][p.y].player == field.getField()[p.x+j+i+1][p.y].player
                        && field.getField()[p.x+j+i+1][p.y].player == field.getField()[p.x+j+i+2][p.y].player
                        && field.getField()[p.x+j+i+2][p.y].player == field.getField()[p.x+j+i+3][p.y].player) {
                    win = true;
                }

                }
            }
        }


    public boolean HoriOutOfBounds(int x, int i){
        if(x+i <= 6 && x+i-3 >=0){
            return false;
        }else{return true;}
    }

    public boolean notNull(Coin c,int j, int i){
        Point p = new Point(c.getLocation());

        if(field.getField()[p.x+j+i][p.y] !=null &&
                 field.getField()[p.x+j+i+1][p.y]!=null
                && field.getField()[p.x+j+i+2][p.y]!=null
                && field.getField()[p.x+j+i+3][p.y]!=null){
            return true;
        }


        return false;
    }

    public boolean VertOutOfBounds(int y, int i){
        if(y+i <= 7 && y+i-3 >=0){
            return false;
        }else{return true;}
    }







}
