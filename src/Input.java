import java.sql.Date;
import java.util.Scanner;

public class Input implements AutoCloseable {
    private Scanner sc;

    public Input (){
        this.sc = new Scanner(System.in);
    }

    public int integer(){
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            sc.next(); // Clear the invalid input
        }
        int value = sc.nextInt();
        sc.nextLine(); // Consume the newline left-over
        return value;
    }
    public String string(){
        return sc.nextLine();
    }
    public double doubleValue() {
        while (!sc.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a double value.");
            sc.next(); // Clear the invalid input
        }
        double value = sc.nextDouble();
        sc.nextLine(); // Consume the newline left-over
        return value;
    }
    public Date date(){
        System.out.println("Enter year: ");
        int year = integer();

        System.out.println("Enter month: ");
        int month = integer();

        System.out.println("Enter day: ");
        int day = integer();

        return new Date(year, month, day);
    }

    public Product product(){
        String ID = null;
        String name = null;
        String description = null;
        int quantity = 0;
        double sellingPrice = 0;
        ProductController productController = new ProductController();

        System.out.println("Enter Product ID: ");
        ID = string();
        while(productController.checkExistence(ID)){
            System.out.println("Product ID already exists. Enter another product ID.");
            ID = string();
        }
        System.out.println("Enter Product Name: ");
        name = string();
        System.out.println("Enter Product Description: ");
        description = string();
        System.out.println("Enter Product Quantity: ");
        quantity = integer();
        System.out.println("Enter Product Selling Price: ");
        sellingPrice = doubleValue();
        return new Product(ID, name, description, quantity, sellingPrice);
    }

    public Customer customer(){
        String ID = null;
        String name = null;
        String email = null;
        String address = null;
        String contactNumber = null;
        Date dateOfBirth = null;
        String gender = null;
        CustomerController customerController = new CustomerController();

        System.out.println("Enter Customer ID: ");
        ID = string();
        while(customerController.checkExistence(ID)){
            System.out.println("Customer ID already exists. Enter another product ID.");
            ID = string();
        }
        System.out.println("Enter Customer Name: ");
        name = string();
        System.out.println("Enter Customer Email: ");
        email = string();
        System.out.println("Enter Customer Address: ");
        address = string();
        System.out.println("Enter Customer Contact Number: ");
        contactNumber = string();
        System.out.println("Enter Customer DOB: ");
        dateOfBirth = date();
        System.out.println("Enter Customer Gender: ");
        gender = string();
        return new Customer(ID, name, email, address, contactNumber, dateOfBirth, gender);
    }

    @Override
    public void close() {
        if (sc != null) {
            sc.close();
        }
    }
}
