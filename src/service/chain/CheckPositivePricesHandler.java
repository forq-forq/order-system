package service.chain;

import domain.Order;
import domain.OrderItem;

public class CheckPositivePricesHandler extends BasicOrderHandler {
    @Override
    protected void process(Order order) {
        for (OrderItem it : order.getItems()) {
            if (it.getProduct().getPrice() <= 0)
                throw new IllegalStateException("Invalid price for " + it.getProduct().getName());
            if (it.getQty() <= 0)
                throw new IllegalStateException("Invalid quantity for " + it.getProduct().getName());
        }
    }
}
