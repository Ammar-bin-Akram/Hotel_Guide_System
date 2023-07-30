import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class Conn{
    Connection c = null;
    public Conn(){
        Statement s = null;
        try{
            Class.forName("org.sqlite.JDBC");
            //c = DriverManager.getConnection("jdbc:sqlite:users.db");
        }
        catch(Exception e){System.out.println(e);}
    }
    public Connection getConnection()throws SQLException {
        c = DriverManager.getConnection("jdbc:sqlite:users.db");
        return c;
    }
}
