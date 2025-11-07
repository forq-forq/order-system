package domain.builder;

import domain.Customer;
import domain.Order;
import domain.OrderItem;
import java.util.ArrayList;
import java.util.List;

public class OrderBuilder {
    private String id;
    private Customer customer;
    private final List<OrderItem> items = new ArrayList<>();

    public OrderBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public OrderBuilder withCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public OrderBuilder addItem(OrderItem item) {
        if (item != null) items.add(item);
        return this;
    }

    public Order build() {
        Order order = new Order();
        order.setId(id);
        order.setCustomer(customer);
        for (OrderItem it : items) {
            order.addItem(it);
        }
        return order;
    }
}
