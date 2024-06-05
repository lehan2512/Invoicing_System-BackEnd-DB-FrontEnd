import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract class DBServices {
    abstract String getTableName();
    abstract void viewData(ResultSet resultSet, String displayID) throws SQLException;
    abstract void addData(PreparedStatement stmt, Connection conn) throws SQLException;

    public DBServices() {}

    public void add()
    {
        Connection con = null;
        PreparedStatement stmt = null;
        DBConnector getCon = new DBConnector();

        try {
            con = getCon.getConnection();
            addData(stmt, con);
        } catch (SQLException e) {
            System.out.println("Cannot find the database");
            e.printStackTrace();

        } finally {
            try{
                if(stmt != null) {
                    stmt.close();
                    stmt = null;
                }

                if(con != null) {
                    con.close();
                    con = null;
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void search(){
        Connection con = null;
        DBConnector getCon = new DBConnector();
        Input input = new Input();

        try {
            con = getCon.getConnection();

            System.out.println("Enter ID: ");
            String ID = input.string();

            if(!checkExistence(ID)) {
                System.out.println("ID does not exist");
            }
            else{
                display(ID);
            }
        }
        finally {
            try{
                if(con != null) {
                    con.close();
                    con = null;
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void remove(){
        Connection con = null;
        PreparedStatement stmt = null;
        DBConnector getCon = new DBConnector();
        Input input = new Input();

        try {
            con = getCon.getConnection();

            System.out.println("Enter ID: ");
            String ID = input.string();

            if(!checkExistence(ID)) {
                System.out.println("ID does not exist");
            }
            else{
                // the transaction to be carried out - SQL statement
                String queryString = "delete from "+ getTableName() +" where ID= ?";                			  		//System.out.println(queryString);

                stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database

                stmt.setString(1, ID);


                int i = stmt.executeUpdate();   // returns an integer - number of 										// records  added

                if (i != 0){
                    System.out.println("Removal successful");
                }
                else{
                    System.out.println("Data update error");
                }
            }
        } catch (SQLException e) {
            System.out.println("Cannot find the database");
            e.printStackTrace();

        } finally {
            try{
                if(stmt != null) {
                    stmt.close();
                    stmt = null;
                }

                if(con != null) {
                    con.close();
                    con = null;
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void display(String displayID)
    {
        Connection con = null;
        PreparedStatement stmt = null;
        DBConnector getCon = new DBConnector();
        ResultSet rs = null;

        try{
            con = getCon.getConnection();

            // retrieve the data in the Names table
            String queryString = "select * from "+ getTableName() +" where ID = ?";

            stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database

            stmt.setString(1, displayID);

            rs = stmt.executeQuery();

            viewData(rs, displayID);

        } catch (SQLException e) {
            System.out.println("Cannot find the database");
            e.printStackTrace();
        }  finally {
            try {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }

                if (con != null) {
                    con.close();
                    con = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    protected boolean checkExistence(String ID) {
        Connection con = null;
        PreparedStatement stmt = null;
        DBConnector getCon = new DBConnector();
        ResultSet rs = null;
        boolean exists = false;

        try {
            con = getCon.getConnection();

            // retrieve the data in table
            String queryString = "select * from "+ getTableName() +" where ID = ?";

            stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database

            stmt.setString(1, ID);

            rs = stmt.executeQuery();

            if (rs.next()) {
                exists = true;
            }

        } catch (SQLException e) {
            System.out.println("Cannot find the database");
            e.printStackTrace();

        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }

                if (con != null) {
                    con.close();
                    con = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return exists;
    }
}
