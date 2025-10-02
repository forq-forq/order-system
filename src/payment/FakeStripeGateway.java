package payment;

public class FakeStripeGateway implements PaymentGateway {
    @Override
    public String pay(String orderId, double amount) {
        return "stripe_tx_" + orderId;
    }
}
