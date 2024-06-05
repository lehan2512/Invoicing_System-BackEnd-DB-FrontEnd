import java.sql.*;
import java.util.Scanner;

public class CustomerController extends DBServices {
    public CustomerController() {}

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

            if(!checkExistence(ID)) {
                System.out.println("Customer does not exist");
            }
            else {
                display(ID);
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
                        display(ID);
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

    @Override
    String getTableName(){
        return "customers";
    }

    @Override
    void viewData(ResultSet resultset, String displayID) throws SQLException{
        if (resultset.next()) {  // Move the cursor to the first row
            String id = resultset.getString("id");
            String name = resultset.getString("name");
            String email = resultset.getString("email");
            String address = resultset.getString("address");
            String contactNumber = resultset.getString("contactNumber");
            Date dateOfBirth = resultset.getDate("dateOfBirth");
            String gender = resultset.getString("gender");

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
    }

    @Override
    void addData(PreparedStatement stmt, Connection con) throws SQLException{
        Input input = new Input();
        Customer customer = input.customer();

        // the transaction to be carried out - SQL statement
        String queryString = "insert into customers (id, name, email, address, contactNumber, dateOfBirth, gender) values (?,?,?,?,?,?,?)";                			  		//System.out.println(queryString);

        stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database

        stmt.setString(1, customer.getID());
        stmt.setString(2, customer.getName());
        stmt.setString(3, customer.getEmail());
        stmt.setString(4, customer.getAddress());
        stmt.setString(5, customer.getContactNumber());
        stmt.setDate(6, customer.getDateOfBirth());
        stmt.setString(7, customer.getGender());

        int i = stmt.executeUpdate();   // returns an integer - number of 										// records  added

        if (i != 0){
            display(customer.getID());
        }
        else{
            System.out.println("Data update error");
        }
    }
}
