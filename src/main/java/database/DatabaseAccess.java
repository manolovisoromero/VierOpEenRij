package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseAccess implements IDatabaseAccess{

    private String url = "jdbc:mysql://localhost:3306/vieropeenrij";
    private String dbusername = "Manolo";
    private String dbpassword = "12345";
    private dbObjectFactory factory = dbObjectFactory.getInstance();

    public Connection connect() {
        try{

            return DriverManager.getConnection(
                    url,dbusername,dbpassword);
        }catch(Exception e){ System.out.println(e);}

        return null;
    }


    @Override
    public void getListOfGames() {

    }

    public ArrayList<logindataDB> getListOfUsers(){
        String testquery;
        try{
            Connection con=DriverManager.getConnection(
                    url,dbusername,dbpassword);

            Statement stmt=con.createStatement();
            testquery = "select * from logindata";

            ResultSet rs=stmt.executeQuery(testquery);
            while(rs.next()) {
                factory.createDBuser(rs.getString(1), rs.getString(2));
            }

            con.close();

        }catch(Exception e){ System.out.println(e);}
        return factory.getLogindatasDB();
    }

    @Override
    public void getListOfGameUsers() {

    }


    public void registerUser(String username, String password) {
        try{
            Connection con=DriverManager.getConnection(
                    url,dbusername,dbpassword);

            Statement stmt=con.createStatement();

            String testquery = "INSERT INTO `logindata`(`Username`, `Password`) VALUES ('"+username+"','"+password+"')";
            stmt.executeUpdate(testquery);

            con.close();

        }catch(Exception e){ System.out.println(e);}


    }
}
