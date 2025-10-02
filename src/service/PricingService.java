package service;

import domain.Order;
import domain.OrderItem;
import policy.DiscountPolicy;

public class PricingService {
    private final DiscountPolicy discountPolicy; // discount logic

    public PricingService(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public void price(Order order) {
        double subtotal = 0;
        for (OrderItem item : order.getItems()) {
            double price = item.getProduct().getPrice();
            int qty = item.getQty();
            subtotal += price * qty;
        }

        order.setSubtotal(subtotal);

        double discount = discountPolicy.calcDiscount(order);
        order.setDiscount(discount);

        order.setTotal(Math.max(0.0, subtotal - discount));
    }
}
