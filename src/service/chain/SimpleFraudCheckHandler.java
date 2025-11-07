package service.chain;

import domain.Order;

public class SimpleFraudCheckHandler extends BasicOrderHandler {
    @Override
    protected void process(Order order) {
        if (order.getSubtotal() > 50000)
            throw new IllegalStateException("Fraud suspicion: subtotal too high");
    }
}
