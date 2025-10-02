
package policy;

import domain.Order;

public interface DiscountPolicy {
    double calcDiscount(Order order); // calculate discount based on order
}
