package payment;

public interface PaymentGateway {
    String pay(String orderId, double amount); // pay and return tx id
}
