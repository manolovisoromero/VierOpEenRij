package database;

import java.sql.Connection;
import java.util.ArrayList;

public interface IDatabaseAccess {

    /**Executes an SELECT query
     * returns list of games
     */
     void getListOfGames();

    /**Executes an SELECT query
     * returns list of users
     */
     ArrayList<logindataDB> getListOfUsers();

    /**Executes an SELECT query
     * returns list of game users
     */
     void getListOfGameUsers();

    /**Establishes DB connection
     * returns connection
     */
     Connection connect();





}
