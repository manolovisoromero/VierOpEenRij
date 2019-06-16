package database;

class gameuserDB {

    gameuserDB(String username, boolean winner, int gameID) {
        this.username = username;
        this.winner = winner;
        this.gameID = gameID;
    }

    private String username;
    private boolean winner;
    private int gameID;

}
