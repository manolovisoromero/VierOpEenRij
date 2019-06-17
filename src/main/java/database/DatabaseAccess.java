package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class DatabaseAccess implements IDatabaseAccess{

    private String url = "jdbc:mysql://localhost:3306/vieropeenrij";
    private String dbusername = "Manolo";
    private String dbpw = "12345";
    private dbObjectFactory factory = dbObjectFactory.getInstance();
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public Connection connect()  {
        try{

            return DriverManager.getConnection(
                    url,dbusername,dbpw);
        }catch(Exception e){             logger.info("Error:"+e);
        }

        return null;
    }


    @Override
    public void getListOfGames() {

    }

    public ArrayList<logindataDB> getListOfUsers() {
        String testquery;
        try (Connection con = DriverManager.getConnection(
                url, dbusername, dbpw)) {

            Statement stmt = con.createStatement();
            testquery = "select * from logindata";

            ResultSet rs = stmt.executeQuery(testquery);
            while (rs.next()) {
                factory.createDBuser(rs.getString(1), rs.getString(2));
            }


        } catch (SQLException e) {
            logger.info("Error:" + e);
        }
        return factory.getLogindatasDB();
    }

    @Override
    public void getListOfGameUsers() {

    }


    public void registerUser(String username, String password){

        try (Connection con = DriverManager.getConnection(
                url, dbusername, dbpw)) {

            Statement stmt = con.createStatement();

            String testquery = "INSERT INTO `logindata`(`Username`, `Password`) VALUES ('" + username + "','" + password + "')";
            stmt.executeUpdate(testquery);

        } catch (SQLException e) {
            logger.info("Error:" + e);
        }


    }
}
