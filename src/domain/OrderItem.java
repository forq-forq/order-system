package domain;

import java.util.Objects;

public class OrderItem {
    private Product product;
    private int qty;

    public OrderItem(Product product, int qty) {
        this.product = product;
        this.qty = qty;
        validate();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        validate();
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
        validate();
    }

    public double getLineTotal() {
        return (product == null) ? 0.0 : product.getPrice() * qty;
    }

    private void validate() {
        if (product == null) {
            return;
        }
        if (qty < 0) {
            throw new IllegalArgumentException("Quantity must be >= 0");
        }
    }

    @Override
    public String toString() {
        String sku = (product == null) ? "null" : product.getSku();
        String name = (product == null) ? "null" : product.getName();
        return "OrderItem{sku=" + sku + ", name=" + name + ", qty=" + qty + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        OrderItem that = (OrderItem) o;
        String s1 = (product == null) ? null : product.getSku();
        String s2 = (that.product == null) ? null : that.product.getSku();
        return Objects.equals(s1, s2);
    }

    @Override
    public int hashCode() {
        String sku = (product == null) ? null : product.getSku();
        return Objects.hash(sku);
    }
}
