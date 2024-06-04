import java.util.Scanner;

public class UserInterface {
    public void startApplication() {
        mainMenu();
    }

    public void mainMenu(){
        Scanner scanner = new Scanner(System.in);
        int mainMenuSelection = 0;

        while (mainMenuSelection != 4) {
            System.out.println("\nER SYSTEM");
            System.out.println("\nMain Menu"+
            "\n1. Manage Products"+
            "\n2. Manage Customers"+
            "\n3. Invoicing System"+
            "\n4. Exit"+
            "\n\nEnter your choice: ");
            mainMenuSelection = scanner.nextInt();

            // validation of user input
            while (mainMenuSelection < 1 || mainMenuSelection > 4) {
                System.out.println("Please enter a number between 1 and 4");
                mainMenuSelection = scanner.nextInt();
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
        scanner.close();
    }

    public void ManageProductsMenu(){
        Scanner scanner = new Scanner(System.in);
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
            ProductMenuSelection = scanner.nextInt();

            // validation of user input
            while (ProductMenuSelection < 1 || ProductMenuSelection > 5) {
                System.out.println("Please enter a number between 1 and 5");
                ProductMenuSelection = scanner.nextInt();
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
        System.out.println("Manage Customers");
    }
    public void InvoicingSystemMenu(){
        System.out.println("Invoicing System");
    }
}
