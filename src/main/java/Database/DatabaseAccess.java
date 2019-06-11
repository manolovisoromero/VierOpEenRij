package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseAccess implements IDatabaseAccess{

    private String url = "jdbc:mysql://localhost:3306/vieropeenrij";
    private String username = "Manolo";
    private String password = "12345";
    private dbObjectFactory factory = dbObjectFactory.getInstance();

    public Connection connect() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    url,username,password);

            return con;
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
                    url,username,password);

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


//    public void queryINSERT() {
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con=DriverManager.getConnection(
//                    url,username,password);
//
//            Statement stmt=con.createStatement();
//
//            //String testquery = "INSERT INTO `leerlingen`(`Name`, `Id`, `Presence`) VALUES ("+"Name"+","+info.getId()+","+info.isPresence()+")";
//            String testquery = "INSERT INTO `leerlingen`(`Name`, `Id`, `Presence`) VALUES ('"+info.getName()+"', 1, true)";
//            System.out.println(testquery);
//            stmt.executeUpdate(testquery);
//
//            con.close();
//
//        }catch(Exception e){ System.out.println(e);}
//
//
//    }
}
