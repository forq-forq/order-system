package domain;

import domain.prototype.Prototype;

public class Product implements Prototype<Product> {
    private String sku;    // product code
    private String name;   // product name
    private double price;  // unit price

    public Product(String sku, String name, double price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    @Override
    public Product clonePrototype() {
        return new Product(this.sku, this.name, this.price);
    }

    public String getSku() { return sku; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    public void setSku(String new_sku) { this.sku = new_sku; }
    public void setName(String new_name) { this.name = new_name; }
    public void setPrice(Double new_price) { this.price = new_price; }
}
