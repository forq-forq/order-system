package policy_factory;

import policy.*;

public class NoDiscountFactory extends DiscountFactory {
    @Override
    public DiscountPolicy createPolicy() {
        return new NoDiscount();
    }
}
