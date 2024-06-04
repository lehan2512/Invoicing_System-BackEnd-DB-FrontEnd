import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductController {
    public ProductController() {}

    public static void addProduct (String ID, String name, String description, int quantity, double sellingPrice)
    {
        Connection con = null;
        PreparedStatement stmt = null;
        DBConnector getCon = new DBConnector();

        try {
            con = getCon.getConnection();

            // the transaction to be carried out - SQL statement
            String queryString = "insert into Stock_Products (id, name, description, quantity, SellingPrice) values (?,?,?,?,?)";                			  		//System.out.println(queryString);

            stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database

            stmt.setString(1, ID);
            stmt.setString(2, name);
            stmt.setString(3, description);
            stmt.setInt(4, quantity);
            stmt.setDouble(5, sellingPrice);

            int i = stmt.executeUpdate();   // returns an integer - number of 										// records  added

            if (i != 0){
                System.out.println("Added product successfully: ID: "+ ID +", Name: "+ name +", Description: "+ description +", Quantity: "+ quantity +", SellingPrice: "+ sellingPrice);
            }
            else{
                System.out.println("Data update error");
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

    public void updateProduct(){
        Connection con = null;
        PreparedStatement stmt = null;
        DBConnector getCon = new DBConnector();
        String queryString = null;

        try {
            con = getCon.getConnection();
            Scanner scan = new Scanner(System.in);

            System.out.println("Enter product ID: ");
            String ID = scan.nextLine();

            if(!checkProductExistence(ID)) {
                System.out.println("Product does not exist");
            }
            else {
                displayProduct(ID);
                System.out.println("Update Product Details"+
                        " \n1. Update Name"+
                        " \n2. Update Description"+
                        " \n3. Update Quantity"+
                        " \n4. Update Selling Price"+
                        " \n5. Cancel"+
                        "\nEnter your choice: ");
                int choice = scan.nextInt();
                scan.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Enter new name: ");
                        String newName = scan.nextLine();

                        queryString = "update Stock_Products set name= ? where ID= ?";
                        stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database

                        stmt.setString(1, newName);
                        stmt.setString(2, ID);

                        int i = stmt.executeUpdate();   // returns an integer - number of 										// records  added

                        if (i != 0){
                            System.out.println("\nProduct update successful");
                            displayProduct(ID);
                        }
                        else{
                            System.out.println("Data update error");
                        }
                        break;
                    case 2:
                        System.out.println("Enter new description: ");
                        String newDescription = scan.nextLine();

                        queryString = "update Stock_Products set description= ? where ID= ?";
                        stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database

                        stmt.setString(1, newDescription);
                        stmt.setString(2, ID);

                        int j = stmt.executeUpdate();   // returns an integer - number of 										// records  added

                        if (j != 0){
                            System.out.println("\nProduct update successful");
                            displayProduct(ID);
                        }
                        else{
                            System.out.println("Data update error");
                        }
                        break;
                    case 3:
                        System.out.println("Enter new Quantity: ");
                        int newQuantity = scan.nextInt();

                        queryString = "update Stock_Products set quantity= ? where ID= ?";
                        stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database

                        stmt.setInt(1, newQuantity);
                        stmt.setString(2, ID);

                        int k = stmt.executeUpdate();   // returns an integer - number of 										// records  added

                        if (k != 0){
                            System.out.println("\nProduct update successful");
                            displayProduct(ID);
                        }
                        else{
                            System.out.println("Data update error");
                        }
                        break;
                    case 4:
                        System.out.println("Enter new Selling Price: ");
                        double newSellingPrice = scan.nextDouble();

                        queryString = "update Stock_Products set sellingPrice= ? where ID= ?";
                        stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database

                        stmt.setDouble(1, newSellingPrice);
                        stmt.setString(2, ID);

                        int l = stmt.executeUpdate();   // returns an integer - number of 										// records  added

                        if (l != 0){
                            System.out.println("\nProduct update successful");
                            displayProduct(ID);
                        }
                        else{
                            System.out.println("Data update error");
                        }
                        break;
                    case 5:
                        break;
                }
            }
            scan.close();

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

    public void searchProduct(){
        Connection con = null;
        DBConnector getCon = new DBConnector();

        try {
            con = getCon.getConnection();
            Scanner scan = new Scanner(System.in);

            System.out.println("Enter product ID: ");
            String ID = scan.nextLine();

            if(!checkProductExistence(ID)) {
                System.out.println("Product does not exist");
            }
            else{
                displayProduct(ID);
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

    public void removeProduct(){
        Connection con = null;
        PreparedStatement stmt = null;
        DBConnector getCon = new DBConnector();

        try {
            con = getCon.getConnection();
            Scanner scan = new Scanner(System.in);

            System.out.println("Enter product ID: ");
            String ID = scan.nextLine();

            if(!checkProductExistence(ID)) {
                System.out.println("Product does not exist");
            }
            else{
                // the transaction to be carried out - SQL statement
                String queryString = "delete from Stock_Products where ID= ?";                			  		//System.out.println(queryString);

                stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database

                stmt.setString(1, ID);


                int i = stmt.executeUpdate();   // returns an integer - number of 										// records  added

                if (i != 0){
                    System.out.println("Product removal successful");
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

    public static boolean checkProductExistence(String ID) {
        Connection con = null;
        PreparedStatement stmt = null;
        DBConnector getCon = new DBConnector();
        ResultSet rs = null;
        boolean exists = false;

        try {
            con = getCon.getConnection();

            // retrieve the data in table
            String queryString = "select * from Stock_Products where ID = ?";

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

    public static void displayProduct(String displayID)
    {
        Connection con = null;
        PreparedStatement stmt = null;
        DBConnector getCon = new DBConnector();
        ResultSet rs = null;

        try{
            con = getCon.getConnection();

            // retrieve the data in the Names table
            String queryString = "select * from Stock_Products where ID = ?";

            stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database

            stmt.setString(1, displayID);

            rs = stmt.executeQuery();

            if (rs.next()) {  // Move the cursor to the first row
                String id = rs.getString("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String quantity = rs.getString("quantity");
                String sellingPrice = rs.getString("sellingPrice");

                System.out.println("Product Details"+
                        "\n           ID: " + id +
                        "\n         Name: "+ name +
                        "\n  Description: "+ description +
                        "\n     Quantity: "+ quantity +
                        "\nSelling Price: "+ sellingPrice + "\n");
            } else {
                System.out.println("No student found with ID = " + displayID);
            }

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

}
