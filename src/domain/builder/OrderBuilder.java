package domain.builder;

import domain.Customer;
import domain.Order;
import domain.OrderItem;
import domain.Product;

import java.util.ArrayList;
import java.util.Collection;

public class OrderBuilder {
    private String id;
    private Customer customer;
    private final ArrayList<OrderItem> items = new ArrayList<>();

    public OrderBuilder id(String id) {
        this.id = id;
        return this;
    }

    public OrderBuilder forCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public OrderBuilder addItem(Product product, int qty) {
        if (product == null) throw new IllegalArgumentException("Empty input");
        if (qty <= 0) throw new IllegalArgumentException("Quantity must be positive integer");
        items.add(new OrderItem(product, qty));
        return this;
    }

    public OrderBuilder addItem(OrderItem item) {
        if (item == null) throw new IllegalArgumentException("Empty input");
        items.add(item);
        return this;
    }

    public OrderBuilder addItems(Collection<OrderItem> src) {
        if (src == null) throw new IllegalArgumentException("Empty input");
        for (OrderItem it : src) addItem(it);
        return this;
    }

    public Order build() {
        if (id == null || id.isBlank()) throw new IllegalStateException("Id can't be empty");
        if (customer == null) throw new IllegalStateException("Customer is required");
        if (items.isEmpty()) throw new IllegalStateException("At least 1 item is required");

        return new Order(id, customer, new ArrayList<>(items));
    }
}
