package service.checkout;

import domain.Order;

public interface CheckoutFacade {
    String checkout(Order order);
}