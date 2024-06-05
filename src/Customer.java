import java.sql.Date;

public class Customer {
    private String ID;
    private String Name;
    private String email;
    private String Address;
    private String contactNumber;
    private Date dateOfBirth;
    private String gender;

    public Customer(String ID, String Name, String email, String address, String contactNumber, Date dateOfBirth, String gender) {
        this.ID = ID;
        this.Name = Name;
        this.email = email;
        this.Address = address;
        this.contactNumber = contactNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }
    public String getID() {
        return ID;
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
    public void setID(String ID) {
        this.ID = ID;
    }
    public void setName(String name) {
        Name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAddress(String address) {
        Address = address;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
}
