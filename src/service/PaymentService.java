package service;

import domain.Order;
import payment.PaymentGateway;
import repo.OrderRepository;

public class PaymentService {
    private final PaymentGateway gateway;  // gateway interface
    private final OrderRepository repo;    // order repo

    public PaymentService(PaymentGateway gateway, OrderRepository repo) {
        this.gateway = gateway;
        this.repo = repo;
    }

    public String payOrder(String orderId) {
        Order order = repo.get(orderId);
        String txId = gateway.pay(order.getId(), order.getTotal());
        order.setPaid(true);
        repo.save(order);
        return txId;
    }
}
