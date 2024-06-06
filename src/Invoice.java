import java.sql.Timestamp;

public class Invoice extends DBElement{
    private final Timestamp dateAndTime;
    private final String customerID;
    private final String customerName;

    public Invoice(String ID, Timestamp dateAndTime, String customerID, String customerName) {
        this.ID = ID;
        this.dateAndTime = dateAndTime;
        this.customerID = customerID;
        this.customerName = customerName;
    }

    public Timestamp getDate() {
        return dateAndTime;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }
}
