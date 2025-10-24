package payment;

public class PayPalAdapter implements PaymentGateway {
    private final PayPalPayment api;

    public PayPalAdapter(PayPalPayment api) {
        this.api = api;
    }

    @Override
    public String pay(String orderId, double amount) {
        String email = orderId.contains("@") ? orderId : orderId + "@example.com";
        long cents = Math.round(amount * 100.0);

        return api.makeTransaction(email, cents);
    }
}
