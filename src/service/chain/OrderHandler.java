package service.chain;

import domain.Order;

public interface OrderHandler {
    OrderHandler setNext(OrderHandler next);
    void handle(Order order);
}
