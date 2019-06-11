package Database;

import java.sql.Connection;
import java.util.ArrayList;

public interface IDatabaseAccess {

    /**Executes an SELECT query
     * returns list of games
     */
    public void getListOfGames();

    /**Executes an SELECT query
     * returns list of users
     */
    public ArrayList<logindataDB> getListOfUsers();

    /**Executes an SELECT query
     * returns list of game users
     */
    public void getListOfGameUsers();

    /**Establishes DB connection
     * returns connection
     */
    public Connection connect();





}
