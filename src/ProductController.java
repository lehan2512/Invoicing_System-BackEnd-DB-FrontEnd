import java.sql.*;
import java.util.Scanner;

public class ProductController extends DBServices {
    public ProductController() {}

    public void updateProduct(){
        Connection con = null;
        PreparedStatement stmt = null;
        DBConnector getCon = new DBConnector();
        String queryString = null;
        int i = -1;
        Input input = new Input();

        try {
            con = getCon.getConnection();
            System.out.println("Enter product ID: ");
            String ID = input.string();

            if(!checkExistence(ID)) {
                System.out.println("Product does not exist");
            }
            else {
                display(ID);
                System.out.println("Update Product Details"+
                        " \n1. Update Name"+
                        " \n2. Update Description"+
                        " \n3. Update Quantity"+
                        " \n4. Update Selling Price"+
                        " \n5. Cancel"+
                        "\nEnter your choice: ");
                int choice = input.integer();

                switch (choice) {
                    case 1:
                        System.out.println("Enter new name: ");
                        String newName = input.string();

                        queryString = "update Stock_Products set name= ? where ID= ?";
                        stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database

                        stmt.setString(1, newName);
                        stmt.setString(2, ID);

                        i = stmt.executeUpdate();   // returns an integer - number of 										// records  added

                        break;
                    case 2:
                        System.out.println("Enter new description: ");
                        String newDescription = input.string();

                        queryString = "update Stock_Products set description= ? where ID= ?";
                        stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database

                        stmt.setString(1, newDescription);
                        stmt.setString(2, ID);

                        i = stmt.executeUpdate();   // returns an integer - number of 										// records  added

                        break;
                    case 3:
                        System.out.println("Enter new Quantity: ");
                        int newQuantity = input.integer();

                        queryString = "update Stock_Products set quantity= ? where ID= ?";
                        stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database

                        stmt.setInt(1, newQuantity);
                        stmt.setString(2, ID);

                        i = stmt.executeUpdate();   // returns an integer - number of 										// records  added

                        break;
                    case 4:
                        System.out.println("Enter new Selling Price: ");
                        double newSellingPrice = input.doubleValue();

                        queryString = "update Stock_Products set sellingPrice= ? where ID= ?";
                        stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database

                        stmt.setDouble(1, newSellingPrice);
                        stmt.setString(2, ID);

                        i = stmt.executeUpdate();   // returns an integer - number of 										// records  added

                        break;
                    case 5:
                        break;
                }
                if(i != -1)
                {
                    if (i != 0){
                        System.out.println("\nCustomer update successful\n");
                        display(ID);
                    }
                    else{
                        System.out.println("Data update error\n");
                    }
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

    @Override
    String getTableName(){
        return "Stock_Products";
    }

    @Override
    void viewData(ResultSet resultSet, String displayID) throws SQLException {
        if (resultSet.next()) {  // Move the cursor to the first row
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String quantity = resultSet.getString("quantity");
            String sellingPrice = resultSet.getString("sellingPrice");

            System.out.println("Product Details"+
                    "\n           ID: " + id +
                    "\n         Name: "+ name +
                    "\n  Description: "+ description +
                    "\n     Quantity: "+ quantity +
                    "\nSelling Price: "+ sellingPrice + "\n");
        } else {
            System.out.println("No data found with ID = " + displayID);
        }
    }

    @Override
    void addData(PreparedStatement stmt, Connection con) throws SQLException{
        Input input = new Input();
        Product product = input.product();

        // the transaction to be carried out - SQL statement
        String queryString = "insert into Stock_Products (id, name, description, quantity, SellingPrice) values (?,?,?,?,?)";                			  		//System.out.println(queryString);

        stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database

        stmt.setString(1, product.getID());
        stmt.setString(2, product.getName());
        stmt.setString(3, product.getDescription());
        stmt.setInt(4, product.getQuantity());
        stmt.setDouble(5, product.getSellingPrice());

        int i = stmt.executeUpdate();   // returns an integer - number of 										// records  added

        if (i != 0){
            System.out.println("Added successfully\n");
            display(product.getID());
        }
        else{
            System.out.println("Data update error");
        }
    }
}
