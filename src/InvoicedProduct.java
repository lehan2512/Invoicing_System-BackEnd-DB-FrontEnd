public class InvoicedProduct {
    private String invoiceID;
    private String productID;
    private int quantity;
    private double unitPrice;
    private double Total;

    public InvoicedProduct(String invoiceID, String productID, int quantity, double unitPrice, double Total) {
        this.invoiceID = invoiceID;
        this.productID = productID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.Total = Total;
    }
    public String getInvoiceID() {
        return invoiceID;
    }
    public String getProductID() {
        return productID;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
    public double getTotal() {
        return Total;
    }
}
