package domain;

public class Product {
    private final String sku;    // product code
    private final String name;   // product name
    private final double price;  // unit price

    public Product(String sku, String name, double price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }
    public String getSku() { return sku; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}
