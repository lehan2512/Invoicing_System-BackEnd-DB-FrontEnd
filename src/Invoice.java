import java.sql.Date;
import java.sql.Timestamp;

public class Invoice {
    private String ID;
    private Timestamp dateAndTime;
    private String customerID;
    private String customerName;

    public Invoice(String ID, Timestamp dateAndTime, String customerID, String customerName) {
        this.ID = ID;
        this.dateAndTime = dateAndTime;
        this.customerID = customerID;
        this.customerName = customerName;
    }
    public String getID() {
        return ID;
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
