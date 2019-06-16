package Database;

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
            Class.forName("com.mysql.jdbc.Driver");

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
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    url,dbusername,dbpassword);

            Statement stmt=con.createStatement();
            testquery = "select * from logindata";

            ResultSet rs=stmt.executeQuery(testquery);
            while(rs.next()) {
                System.out.println(rs.getString(1) + rs.getString(2));
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
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    url,dbusername,dbpassword);

            Statement stmt=con.createStatement();

            String testquery = "INSERT INTO `logindata`(`Username`, `Password`) VALUES ('"+username+"','"+password+"')";
            System.out.println(testquery);
            stmt.executeUpdate(testquery);

            con.close();

        }catch(Exception e){ System.out.println(e);}


    }
}
