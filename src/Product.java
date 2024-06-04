import java.sql.Date;

public class Product {
    private String ID;
    private String name;
    private String description;
    private int quantity;
    private double sellingPrice;
    private Date createDate;
    private Date lastUpdate;

    public Product() {};
    public Product(String ID, String name, String description, int quantity, double sellingPrice) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
    }

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getSellingPrice() {
        return sellingPrice;
    }
    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
