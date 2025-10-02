package policy;

import domain.Order;

public class PercentageOverThreshold implements DiscountPolicy {
    private final double threshold; // min subtotal
    private final double percent;   // discount %

    public PercentageOverThreshold(double threshold, double percent) {
        this.threshold = threshold;
        this.percent = percent;
    }

    @Override
    public double calcDiscount(Order order) {
        return order.getSubtotal() > threshold
                ? order.getSubtotal() * (percent / 100.0)
                : 0.0;
    }
}
