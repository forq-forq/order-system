package service.chain;

import domain.Order;

public abstract class BasicOrderHandler implements OrderHandler {
    private OrderHandler next;

    @Override
    public OrderHandler setNext(OrderHandler next) {
        this.next = next;
        return next;
    }

    @Override
    public void handle(Order order) {
        process(order);
        if (next != null) next.handle(order);
    }

    protected abstract void process(Order order);
}
