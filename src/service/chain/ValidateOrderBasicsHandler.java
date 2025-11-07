package service.chain;

import domain.Order;

public class ValidateOrderBasicsHandler extends BasicOrderHandler {
    @Override
    protected void process(Order order) {
        if (order == null) throw new IllegalArgumentException("Order is null");
        if (order.getItems() == null || order.getItems().isEmpty())
            throw new IllegalStateException("Order has no items");
        if (order.getCustomer() == null)
            throw new IllegalStateException("Order has no customer");
    }
}
