package policy_factory;

import policy.*;

public class ThresholdDiscountFactory extends DiscountFactory {
    private final double threshold;
    private final double percent;

    public ThresholdDiscountFactory(double threshold, double percent) {
        this.threshold = threshold;
        this.percent = percent;
    }

    @Override
    public DiscountPolicy createPolicy() {
        return new PercentageOverThreshold(threshold, percent);
    }
}

