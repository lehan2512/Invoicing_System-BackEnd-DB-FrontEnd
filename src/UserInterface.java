import java.sql.Date;
import java.util.Scanner;

public class UserInterface {
    public void startApplication() {
        mainMenu();
    }

    public void mainMenu(){
        Input mainMenuInput = new Input();
        int mainMenuSelection = 0;

        while (mainMenuSelection != 4) {
            System.out.println("\nER SYSTEM");
            System.out.println("\nMain Menu"+
            "\n1. Manage Products"+
            "\n2. Manage Customers"+
            "\n3. Invoicing System"+
            "\n4. Exit"+
            "\n\nEnter your choice: ");
            mainMenuSelection = mainMenuInput.integer();

            // validation of user input
            while (mainMenuSelection < 1 || mainMenuSelection > 4) {
                System.out.println("Please enter a number between 1 and 4");
                mainMenuSelection = mainMenuInput.integer();
            }

            switch (mainMenuSelection) {
                case 1:
                    ManageProductsMenu();
                    break;
                case 2:
                    ManageCustomersMenu();
                    break;
                case 3:
                    InvoicingSystemMenu();
                    break;
                case 4:
                    System.out.println("Exit program");
                    break;
            }
        }
    }

    public void ManageProductsMenu(){
        Input productMenuInput = new Input();
        int ProductMenuSelection = 0;

        while (ProductMenuSelection != 5) {
            System.out.println("\nER SYSTEM");
            System.out.println("\nProduct Menu"+
                    "\n1. Add Product"+
                    "\n2. Update Product"+
                    "\n3. Search Product"+
                    "\n4. Remove Product"+
                    "\n5. Back to Main Menu"+
                    "\n\nEnter your choice: ");
            ProductMenuSelection = productMenuInput.integer();

            // validation of user input
            while (ProductMenuSelection < 1 || ProductMenuSelection > 5) {
                System.out.println("Please enter a number between 1 and 5");
                ProductMenuSelection = productMenuInput.integer();
            }

            ProductController productController = new ProductController();
            switch (ProductMenuSelection) {
                case 1:
                    productController.addProduct("002", "Toys", "Kids playing item", 500, 1500.50);
                    break;
                case 2:
                    productController.updateProduct();
                    break;
                case 3:
                    productController.searchProduct();
                    break;
                case 4:
                    productController.removeProduct();
                    break;
                case 5:
                    break;
            }
        }
    }
    public void ManageCustomersMenu(){
        Input customerMenuInput = new Input();
        int CustomerMenuSelection = 0;

        while (CustomerMenuSelection != 5) {
            System.out.println("\nER SYSTEM");
            System.out.println("\nCustomer Menu"+
                    "\n1. Add Customer"+
                    "\n2. Update Customer"+
                    "\n3. Search Customer"+
                    "\n4. Remove Customer"+
                    "\n5. Back to Main Menu"+
                    "\n\nEnter your choice: ");
            CustomerMenuSelection = customerMenuInput.integer();

            // validation of user input
            while (CustomerMenuSelection < 1 || CustomerMenuSelection > 5) {
                System.out.println("Please enter a number between 1 and 5");
                CustomerMenuSelection = customerMenuInput.integer();
            }

            CustomerController customerController = new CustomerController();
            switch (CustomerMenuSelection) {
                case 1:
                    Date date = new Date(1990, 1, 1);
                    customerController.addCustomer();
                    break;
                case 2:
                    customerController.updateCustomer();
                    break;
                case 3:
                    customerController.searchCustomer();
                    break;
                case 4:
                    customerController.removeCustomer();
                    break;
                case 5:
                    break;
            }
        }
    }
    public void InvoicingSystemMenu(){
        System.out.println("Invoicing System");
    }
}
