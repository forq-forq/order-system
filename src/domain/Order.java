package domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Order implements Cloneable {
    private String id;
    private Customer customer;
    private final List<OrderItem> items = new ArrayList<>();
    private double subtotal;
    private double discount;
    private double total;
    private boolean paid;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public List<OrderItem> getItems() { return items; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public boolean isPaid() { return paid; }
    public void setPaid(boolean paid) { this.paid = paid; }

    public void addItem(OrderItem item) {
        if (item == null || item.getProduct() == null) return;
        String sku = item.getProduct().getSku();
        for (OrderItem it : items) {
            if (sameSku(it, sku)) {
                it.setQty(it.getQty() + item.getQty());
                return;
            }
        }
        items.add(item);
    }

    public OrderItem removeItem(String sku) {
        if (sku == null) return null;
        Iterator<OrderItem> it = items.iterator();
        while (it.hasNext()) {
            OrderItem cur = it.next();
            if (sameSku(cur, sku)) {
                it.remove();
                return cur;
            }
        }
        return null;
    }

    private boolean sameSku(OrderItem it, String sku) {
        return it.getProduct() != null &&
               Objects.equals(it.getProduct().getSku(), sku);
    }

    @Override
    public Order clone() {
        try {
            Order copy = (Order) super.clone();
            List<OrderItem> newItems = new ArrayList<>();
            for (OrderItem it : this.items) {
                OrderItem cloned = new OrderItem(it.getProduct(), it.getQty());
                newItems.add(cloned);
            }
            copy.getItems().clear();
            copy.getItems().addAll(newItems);

            copy.setId(this.id);
            copy.setCustomer(this.customer);
            copy.setSubtotal(this.subtotal);
            copy.setDiscount(this.discount);
            copy.setTotal(this.total);
            copy.setPaid(this.paid);
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
