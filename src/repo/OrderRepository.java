package repo;

import domain.Order;

public interface OrderRepository {
    void save(Order order);     // save order
    Order get(String orderId);  // get order by id
}
