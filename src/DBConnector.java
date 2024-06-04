import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    public static Connection getConnection() {
        Connection con = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");  // loading the MySQL driver

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoicing_system","root","");
            // odbc_ex is the data source name (DSN) -- Access related
        }
        catch (SQLException e) {
            System.out.println("Cannot find the database");
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            System.out.println("Cannot find the driver");
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
