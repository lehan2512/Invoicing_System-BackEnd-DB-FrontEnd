import java.sql.Date;

public class Customer extends DBElement{
    private final String Name;
    private final String email;
    private final String Address;
    private final String contactNumber;
    private final Date dateOfBirth;
    private final String gender;

    public Customer(String ID, String Name, String email, String address, String contactNumber, Date dateOfBirth, String gender) {
        this.ID = ID;
        this.Name = Name;
        this.email = email;
        this.Address = address;
        this.contactNumber = contactNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return Address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }
}
