package Database;

import java.util.ArrayList;

public class dbObjectFactory {

    private static dbObjectFactory dbfactory = new dbObjectFactory();

    public static dbObjectFactory getInstance(){
        return dbfactory;
    }

    public dbObjectFactory(){

    }

    private ArrayList<GameDB> gamesDB = new ArrayList<>();
    private ArrayList<gameuserDB> gameusersDB = new ArrayList<>();
    private ArrayList<logindataDB> logindatasDB = new ArrayList<>();

    public ArrayList<GameDB> getGamesDB() { return gamesDB; }

    public void setGamesDB(ArrayList<GameDB> gamesDB) { this.gamesDB = gamesDB; }

    public ArrayList<gameuserDB> getGameusersDB() { return gameusersDB; }

    public void setGameusersDB(ArrayList<gameuserDB> gameusersDB) { this.gameusersDB = gameusersDB; }

    public ArrayList<logindataDB> getLogindatasDB() { return logindatasDB; }

    public void setLogindatasDB(ArrayList<logindataDB> logindatasDB) { this.logindatasDB = logindatasDB; }

    public void createDBgame(int gameID, int moves){
        GameDB gameDB = new GameDB(gameID,moves);
        gamesDB.add(gameDB);
    }

    public void createDBgameuser(String username, boolean winner, int gameID){
        gameuserDB gameuserDB = new gameuserDB(username,winner,gameID);
        gameusersDB.add(gameuserDB);

    }

    public void createDBuser(String username, String password){
        logindataDB logindataDB = new logindataDB(username, password);
        logindatasDB.add(logindataDB);
    }







}
