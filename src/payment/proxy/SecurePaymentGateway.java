package payment.proxy;

import domain.Order;
import payment.PaymentGateway;
import repo.OrderRepository;

public class SecurePaymentGateway implements PaymentGateway {
    private final PaymentGateway realGateway;
    private final OrderRepository repo;

    public SecurePaymentGateway(PaymentGateway realGateway, OrderRepository repo) {
        this.realGateway = realGateway;
        this.repo = repo;
    }

    @Override
    public String pay(String orderId, double amount) {
        Order order = repo.get(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order not found: " + orderId);
        }
        if (order.isPaid()) {
            throw new IllegalStateException("Order already paid: " + orderId);
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount: " + amount);
        }

        System.out.println("[Proxy] Processing payment for order " + orderId + " amount $" + amount);
        String tx = realGateway.pay(orderId, amount);
        System.out.println("[Proxy] Payment completed, transaction id: " + tx);
        return tx;
    }
}
