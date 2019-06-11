package Database;

public class gameuserDB {

    public gameuserDB(String username, boolean winner, int gameID) {
        this.username = username;
        this.winner = winner;
        this.gameID = gameID;
    }

    public String username;
    public boolean winner;
    public int gameID;

}
