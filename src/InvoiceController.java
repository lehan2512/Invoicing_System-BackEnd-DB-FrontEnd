import java.sql.*;

public class InvoiceController extends DBServices {
    public InvoiceController() {}

    public double displayInvoicedProducts(String invoiceID){
        Connection con = null;
        PreparedStatement stmt = null;
        DBConnector getCon = new DBConnector();
        ResultSet rs = null;
        double invoiceTotal = 0.0;

        try{
            con = getCon.getConnection();


            // retrieve the data in the Names table
            String queryString = "select * from invoiced_products where invoiceID= ?";

            stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database
            stmt.setString(1, invoiceID);
            rs = stmt.executeQuery();

            while (rs.next()){
                String productID = rs.getString("productID");   //
                int quantity = rs.getInt("quantity");
                double unitPrice = rs.getDouble("unitPrice");
                double total = rs.getDouble("total");
                invoiceTotal += total;

                ProductController pc = new ProductController();
                Product product = pc.getProduct(productID);
                String productName = product.getName();

                System.out.println("Product:"+ productName +" Quantity:"+ quantity +"Unit Price:"+ unitPrice +" Total:"+ total);
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
            }

        }
        return invoiceTotal;
    }

    public void searchInvoice(){
        Input input = new Input();
        System.out.println("Enter invoice ID: ");
        String invoiceID = input.string();
        InvoiceController controller = new InvoiceController();
        if(!controller.checkExistence(invoiceID)){
            System.out.println("Invoice not found");
        }
        else{
            displayInvoice(invoiceID);
        }
    }

    public void searchInvoicesByCustomerID(){
        Input input = new Input();
        System.out.println("Enter customer ID: ");
        String customerID = input.string();

        CustomerController controller = new CustomerController();
        while(!controller.checkExistence(customerID)){
            System.out.println("Customer not found");
            customerID = input.string();
        }

        System.out.println("\n\nInvoices of customer ID: " + customerID);

        Connection con = null;
        PreparedStatement stmt = null;
        DBConnector getCon = new DBConnector();
        ResultSet resultset = null;
        Invoice invoice = null;

        try{
            con = getCon.getConnection();

            // retrieve the data in the table
            String queryString = "select * from invoices where customerID = ?";

            stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database
            stmt.setString(1, customerID);
            resultset = stmt.executeQuery();

            if (resultset.next()) {  // Move the cursor to the first row
                String id = resultset.getString("id");
                Timestamp dateAndTime = resultset.getTimestamp("date");

                System.out.println("Invoice ID:"+id+ "       Date and time:"+dateAndTime);
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

    public Invoice getInvoice(String ID){
        Connection con = null;
        PreparedStatement stmt = null;
        DBConnector getCon = new DBConnector();
        ResultSet resultset = null;
        Invoice invoice = null;

        try{
            con = getCon.getConnection();

            // retrieve the data in the table
            String queryString = "select * from invoices where ID = ?";

            stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database
            stmt.setString(1, ID);
            resultset = stmt.executeQuery();

            if (resultset.next()) {  // Move the cursor to the first row
                String id = resultset.getString("id");
                Timestamp dateAndTime = resultset.getTimestamp("date");
                String customerID = resultset.getString("customerID");
                String customerName = resultset.getString("customerName");

                invoice = new Invoice(id, dateAndTime, customerID, customerName);
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
        return invoice;
    }

    @Override
    String getTableName(){
        return "invoices";
    }

    @Override
    void viewData(ResultSet resultset, String displayID) throws SQLException {
        if (resultset.next()) {  // Move the cursor to the first row
            String id = resultset.getString("id");
            Timestamp dateAndTime = resultset.getTimestamp("date");
            String customerID = resultset.getString("customerID");
            String customerName = resultset.getString("customerName");


            System.out.println("Invoice Details"+
                    "\n            ID: " + id +
                    "\n   dateAndTime: "+ dateAndTime +
                    "\n    customerID: "+ customerID +
                    "\n  customerName: "+ customerName + "\n");
        } else {
            System.out.println("No result found with ID = " + displayID);
        }
    }

    @Override
    void addData(PreparedStatement stmt, Connection con) throws SQLException {
        Input input = new Input();
        Invoice invoice = input.invoice();

        // the transaction to be carried out - SQL statement
        String queryString = "insert into invoices (id, customerID, customerName) values (?,?,?)";                			  		//System.out.println(queryString);

        stmt = con.prepareStatement(queryString);  // creating the statement object to work with 									//database

        stmt.setString(1, invoice.getID());
        stmt.setString(2, invoice.getCustomerID());
        stmt.setString(3, invoice.getCustomerName());

        int i = stmt.executeUpdate();   // returns an integer - number of 										// records  added
        if (i != 0){
            display(invoice.getID());
        }
        else{
            System.out.println("Data update error");
        }

        System.out.println("Add product");
        checkoutProduct(invoice.getID());
        boolean addMoreProducts = true;
        String choice = null;
        while (addMoreProducts){
            System.out.println("Add more products (y/n): ");
            choice = input.string();
            if (choice.equalsIgnoreCase("y")){
                checkoutProduct(invoice.getID());
            }
            else if (choice.equalsIgnoreCase("n")){
                addMoreProducts = false;
            }
        }
        displayInvoice(invoice.getID());
    }

    private void displayInvoice(String invoiceID) {

        Invoice invoice = getInvoice(invoiceID);
        Timestamp date = invoice.getDate();
        String customerID = invoice.getCustomerID();
        String customerName = invoice.getCustomerName();

        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
        System.out.println("                                         SALES INVOICE                                              \n");
        System.out.println(" Invoice Date: " + date);
        System.out.println(" Invoice ID: " + invoiceID);
        System.out.println(" Customer ID: " + customerID);
        System.out.println(" Customer Name: " + customerName);
        System.out.println("\n Products");
        double invoicedTotal = displayInvoicedProducts(invoiceID);
        System.out.println("\nTotal Bill: " + invoicedTotal);
        System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
    }
}
