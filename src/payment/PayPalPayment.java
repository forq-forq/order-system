package payment;

public class PayPalPayment {
    public String makeTransaction(String userEmail, long amountCents) {
        System.out.println("PayPal: processing " + amountCents + " cents for " + userEmail);
        return "paypal_tx_" + System.currentTimeMillis();
    }
}
