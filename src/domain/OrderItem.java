package domain;

public class OrderItem {
    private final Product product;    // product reference
    private final int qty;            // quantity

    public OrderItem(Product product, int qty) {
        this.product = product;
        this.qty = qty;
    }
    public Product getProduct() { return product; }
    public int getQty() { return qty; }
}
