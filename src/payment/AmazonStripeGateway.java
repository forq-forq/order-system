package payment;

public class AmazonStripeGateway implements PaymentGateway {
    @Override
    public String pay(String orderId, double amount) {
        return "amazon_stripe_tx_" + orderId;
    }
}
