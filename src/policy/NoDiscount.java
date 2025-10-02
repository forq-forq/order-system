package policy;

import domain.Order;

public class NoDiscount implements DiscountPolicy {
    @Override
    public double calcDiscount(Order order) {
        return 0.0;
    }
}
