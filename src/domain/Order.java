package domain;

import domain.prototype.Prototype;
import java.util.ArrayList;

public class Order implements Prototype<Order> {
    private final String id;                     // order id
    private final Customer customer;             // customer reference
    private final ArrayList<OrderItem> items;    // order id

    private double subtotal;    // sum before discount
    private double discount;    // discount applied
    private double total;       // total after discount
    private boolean paid;       // payment status

    public Order(String id, Customer customer, ArrayList<OrderItem> items) {
        this.id = id;
        this.customer = customer;
        this.items = items;
        this.subtotal = 0.0;
        this.discount = 0.0;
        this.total = 0.0;
        this.paid = false;
    }

    @Override
    public Order clonePrototype() {
        ArrayList<OrderItem> clonedItems = new ArrayList<>();
        for (OrderItem item : items) {
            clonedItems.add(new OrderItem(item.getProduct(), item.getQty()));
        }
        return new Order(this.id + "-copy", this.customer, clonedItems);
    }

    public String getId() { return id; }
    public Customer getCustomer() { return customer; }
    public ArrayList<OrderItem> getItems() { return items; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public boolean isPaid() { return paid; }
    public void setPaid(boolean paid) { this.paid = paid; }
}
