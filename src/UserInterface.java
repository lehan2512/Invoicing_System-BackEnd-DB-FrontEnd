import java.sql.Date;
import java.util.Scanner;

public class UserInterface {
    public void runApplication() {
        mainMenu();
    }

    private void mainMenu(){
        Input input = new Input();
        int mainMenuSelection = 0;

        while (mainMenuSelection != 4) {
            System.out.println("\nER SYSTEM");
            System.out.println("\nMain Menu"+
            "\n1. Manage Products"+
            "\n2. Manage Customers"+
            "\n3. Invoicing System"+
            "\n4. Exit"+
            "\n\nEnter your choice: ");
            mainMenuSelection = input.integer();

            // validation of user input
            while (mainMenuSelection < 1 || mainMenuSelection > 4) {
                System.out.println("Please enter a number between 1 and 4");
                mainMenuSelection = input.integer();
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

    private void ManageProductsMenu(){
        Input input = new Input();
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
            ProductMenuSelection = input.integer();

            // validation of user input
            while (ProductMenuSelection < 1 || ProductMenuSelection > 5) {
                System.out.println("Please enter a number between 1 and 5");
                ProductMenuSelection = input.integer();
            }

            ProductController productController = new ProductController();
            switch (ProductMenuSelection) {
                case 1:
                    productController.add();
                    break;
                case 2:
                    productController.updateProduct();
                    break;
                case 3:
                    productController.search();
                    break;
                case 4:
                    productController.remove();
                    break;
                case 5:
                    break;
            }
        }
    }
    private void ManageCustomersMenu(){
        Input input = new Input();
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
            CustomerMenuSelection = input.integer();

            // validation of user input
            while (CustomerMenuSelection < 1 || CustomerMenuSelection > 5) {
                System.out.println("Please enter a number between 1 and 5");
                CustomerMenuSelection = input.integer();
            }

            CustomerController customerController = new CustomerController();
            switch (CustomerMenuSelection) {
                case 1:
                    customerController.add();
                    break;
                case 2:
                    customerController.updateCustomer();
                    break;
                case 3:
                    customerController.search();
                    break;
                case 4:
                    customerController.remove();
                    break;
                case 5:
                    break;
            }
        }
    }
    private void InvoicingSystemMenu(){
        Input input = new Input();
        int InvoiceMenuSelection = 0;

        while (InvoiceMenuSelection != 3) {
            System.out.println("\nER SYSTEM");
            System.out.println("\nInvoice Menu"+
                    "\n1. Create New Invoice"+
                    "\n2. View Invoices"+
                    "\n3. View Invoices by Customer ID"+
                    "\n4. Back to Main Menu"+
                    "\n\nEnter your choice: ");
            InvoiceMenuSelection = input.integer();

            // validation of user input
            while (InvoiceMenuSelection < 1 || InvoiceMenuSelection > 4) {
                System.out.println("Please enter a number between 1 and 5");
                InvoiceMenuSelection = input.integer();
            }

            InvoiceController invoiceController = new InvoiceController();
            switch (InvoiceMenuSelection) {
                case 1:
                    invoiceController.add();
                    break;
                case 2:
                    invoiceController.searchInvoice();
                    break;
                case 3:
                    invoiceController.searchInvoicesByCustomerID();
                    break;
                case 4:
                    break;
            }
        }
    }
}
