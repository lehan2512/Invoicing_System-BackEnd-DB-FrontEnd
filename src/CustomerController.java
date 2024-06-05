import java.sql.*;
import java.util.Scanner;

public class CustomerController {
    public CustomerController() {}

    public static void addCustomer ()
    {
        Input input = new Input();
        String ID = null;
        String name = null;
        String email = null;
        String address = null;
        String contactNumber = null;
        Date dateOfBirth = null;
        String gender = null;

        System.out.println("Enter Customer ID: ");
        ID = input.string();
        System.out.println("Enter Customer Name: ");
        name = input.string();
        System.out.println("Enter Customer Email: ");
        email = input.string();
        System.out.println("Enter Customer Address: ");
        address = input.string();
        System.out.println("Enter Customer Contact Number: ");
        contactNumber = input.string();
        System.out.println("Enter Customer DOB: ");
        dateOfBirth = input.date();
        System.out.println("Enter Customer Gender: ");
        gender = input.string();

        Connection con = null;
        PreparedStatement stmt = null;
        DBConnector getCon = new DBConnector();

        try {
            con = getCon.getConnection();

            // the transaction to be carried out - SQL statement
            String queryString = "insert into customers (id, name, email, address, contactNumber, dateOfBirth, gender) values (?,?,?,?,?,?,?)";                			  		//System.out.println(queryString);

            stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database

            stmt.setString(1, ID);
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.setString(4, address);
            stmt.setString(5, contactNumber);
            stmt.setDate(6, dateOfBirth);
            stmt.setString(7, gender);

            int i = stmt.executeUpdate();   // returns an integer - number of 										// records  added

            if (i != 0){
                displayCustomer(ID);
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

    public void updateCustomer(){
        Connection con = null;
        PreparedStatement stmt = null;
        DBConnector getCon = new DBConnector();
        String queryString = null;
        Input input = new Input();

        try {
            con = getCon.getConnection();

            System.out.println("Enter customer ID: ");
            String ID = input.string();

            if(!checkCustomerExistence(ID)) {
                System.out.println("Customer does not exist");
            }
            else {
                displayCustomer(ID);
                System.out.println("Update Customer Details"+
                        " \n1. Update Name"+
                        " \n2. Update Email Address"+
                        " \n3. Update Address"+
                        " \n4. Update Contact Number"+
                        " \n5. Update Date of Birth"+
                        " \n6. Update Gender"+
                        " \n7. Cancel"+
                        "\nEnter your choice: ");
                int choice = input.integer();
                int i = -1;
                switch (choice) {
                    case 1:
                        System.out.println("Enter new name: ");
                        String newName = input.string();

                        queryString = "update customers set name= ? where ID= ?";
                        stmt = con.prepareStatement(queryString);  // creating the statement object to work with

                        stmt.setString(1, newName);
                        stmt.setString(2, ID);
                        i = stmt.executeUpdate();   // returns an integer - number of

                        break;
                    case 2:
                        System.out.println("Enter new Email Address: ");
                        String newEmail = input.string();

                        queryString = "update customers set email= ? where ID= ?";
                        stmt = con.prepareStatement(queryString);  // creating the statement object to work with

                        stmt.setString(1, newEmail);
                        stmt.setString(2, ID);
                        i = stmt.executeUpdate();   // returns an integer - number of

                        break;
                    case 3:
                        System.out.println("Enter new Address: ");
                        String newAddress = input.string();

                        queryString = "update customers set address= ? where ID= ?";
                        stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database

                        stmt.setString(1, newAddress);
                        stmt.setString(2, ID);

                        i = stmt.executeUpdate();   // returns an integer - number of 										// records  added

                        break;
                    case 4:
                        System.out.println("Enter new contact number: ");
                        String newContactNumber = input.string();

                        queryString = "update customers set contactNumber= ? where ID= ?";
                        stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database

                        stmt.setString(1, newContactNumber);
                        stmt.setString(2, ID);

                        i = stmt.executeUpdate();   // returns an integer - number of 										// records  added

                        break;
                    case 5:
                        Date newDateOfBirth = input.date();

                        queryString = "update customers set dateOfBirth= ? where ID= ?";
                        stmt = con.prepareStatement(queryString);  // creating the statement object to work with

                        stmt.setDate(1, newDateOfBirth);
                        stmt.setString(2, ID);
                        i = stmt.executeUpdate();   // returns an integer - number of

                    case 6:
                        System.out.println("Enter new gender: ");
                        String newGender = input.string();

                        queryString = "update customers set gender= ? where ID= ?";
                        stmt = con.prepareStatement(queryString);  // creating the statement object to work with

                        stmt.setString(1, newGender);
                        stmt.setString(2, ID);
                        i = stmt.executeUpdate();   // returns an integer - number of

                        break;
                    case 7:
                        break;
                }
                if(i != -1)
                {
                    if (i != 0){
                        System.out.println("\nCustomer update successful");
                        displayCustomer(ID);
                    }
                    else{
                        System.out.println("Data update error");
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

    public void searchCustomer(){
        Connection con = null;
        DBConnector getCon = new DBConnector();
        Input input = new Input();

        try {
            con = getCon.getConnection();

            System.out.println("Enter Customer ID: ");
            String ID = input.string();

            if(!checkCustomerExistence(ID)) {
                System.out.println("Customer does not exist");
            }
            else{
                displayCustomer(ID);
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

    public void removeCustomer(){
        Connection con = null;
        PreparedStatement stmt = null;
        DBConnector getCon = new DBConnector();
        Input input = new Input();

        try {
            con = getCon.getConnection();

            System.out.println("Enter customer ID: ");
            String ID = input.string();

            if(!checkCustomerExistence(ID)) {
                System.out.println("Customer does not exist");
            }
            else{
                // the transaction to be carried out - SQL statement
                String queryString = "delete from customers where ID= ?";                			  		//System.out.println(queryString);

                stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database

                stmt.setString(1, ID);


                int i = stmt.executeUpdate();   // returns an integer - number of 										// records  added

                if (i != 0){
                    System.out.println("Customer removal successful");
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

    public static boolean checkCustomerExistence(String ID) {
        Connection con = null;
        PreparedStatement stmt = null;
        DBConnector getCon = new DBConnector();
        ResultSet rs = null;
        boolean exists = false;

        try {
            con = getCon.getConnection();

            // retrieve the data in table
            String queryString = "select * from customers where ID = ?";

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

    public static void displayCustomer(String displayID)
    {
        Connection con = null;
        PreparedStatement stmt = null;
        DBConnector getCon = new DBConnector();
        ResultSet rs = null;

        try{
            con = getCon.getConnection();

            // retrieve the data in the Names table
            String queryString = "select * from customers where ID = ?";

            stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database

            stmt.setString(1, displayID);

            rs = stmt.executeQuery();

            if (rs.next()) {  // Move the cursor to the first row
                String id = rs.getString("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String contactNumber = rs.getString("contactNumber");
                Date dateOfBirth = rs.getDate("dateOfBirth");
                String gender = rs.getString("gender");

                System.out.println("Customer Details"+
                        "\n            ID: " + id +
                        "\n          Name: "+ name +
                        "\n         Email: "+ email +
                        "\n       Address: "+ address +
                        "\nContact Number: "+ contactNumber +
                        "\n Date of birth: "+ dateOfBirth +
                        "\n        Gender: "+ gender + "\n");
            } else {
                System.out.println("No result found with ID = " + displayID);
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
