public class Product extends DBElement {
    private final String name;
    private final String description;
    private final int quantity;
    private final double sellingPrice;

    public Product(String ID, String name, String description, int quantity, double sellingPrice) {
        super();
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }
}