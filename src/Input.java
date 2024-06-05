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

    @Override
    public void close() {
        if (sc != null) {
            sc.close();
        }
    }
}
