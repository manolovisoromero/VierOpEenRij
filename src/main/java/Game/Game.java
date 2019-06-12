package Game;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import REST.Server;
import Websocketserver.IObserver;
import Websocketserver.ServerLogic;
import javafx.scene.paint.Color;


public class Game implements ISubject{

    private int gameID;

    private ArrayList<IObserver> observers = new ArrayList<IObserver>();

    private ServerLogic serverLogic;


    public Coin getLastPlayed() {
        return lastPlayed;
    }

    public Coin lastPlayed;

    public Field getField() {
        return field;
    }

    public boolean win = false;

    Random random = new Random();

    private Field field = new Field(this);
    private ArrayList<Player> players;

    public Game(ArrayList<Player> players, ServerLogic serverLogic,int gameID){
        this.players = players;
        this.serverLogic = serverLogic;
        Attach(serverLogic);
        this.gameID = gameID;
    }

    public Player randomStart(ArrayList<Player> players){
        return players.get(random.nextInt(2));
    }

    public void playCoin(Player player, int x) throws IOException {
        if(Available(x)) {
            Coin c = new Coin(player, decideColor(player),whereToDrop(x));
            getField().addCoin(c);
            lastPlayed = c;
            HorizontalWin(c);
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
                }else{return lastavailable;}
            }else{bottomhit = true;}
        }
        return lastavailable;
    }


    public boolean Available(int x){
        return field.getField()[x][1] == null;
    }

    public void HorizontalWin(Coin c) throws IOException {
        int j = -3;
        Point p = new Point(c.getLocation());
        for(int i =0;i<4;i++){
            if(!HoriOutOfBounds(p.x,i )){
                if(notNull(c,j,i)){
                if(field.getField()[p.x+j+i][p.y].player == field.getField()[p.x+j+i+1][p.y].player
                        && field.getField()[p.x+j+i+1][p.y].player == field.getField()[p.x+j+i+2][p.y].player
                        && field.getField()[p.x+j+i+2][p.y].player == field.getField()[p.x+j+i+3][p.y].player) {
                    win();
                }
                }}
            }
        }




    public void win() throws IOException {
        win = true;
        getLastPlayed().player.setWin(true);
        Notify();
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






    public void GameEnd(){
        //losing player boolean lose, winner player boolean win
        //Save game to database:: Games: int game id(fk), int (amount of) moves; Game users: str user, bool win, int game id(pk)
        //msg players
        //
    }


    @Override
    public void Attach(IObserver o) {
        observers.add(o);


    }

    @Override
    public void Detach(IObserver o) {

        observers.remove(o);


    }

    @Override
    public void Notify() throws IOException {

        for(int i = 0; i< observers.size(); i++){
            observers.get(i).update(this);
        }

    }
}
