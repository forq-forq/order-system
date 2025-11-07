package command;

import domain.Order;
import repo.OrderRepository;
import service.PaymentService;

public class PayOrderCommand implements Command {
    private final PaymentService payments;
    private final OrderRepository repo;
    private final String orderId;
    private String txId;

    public PayOrderCommand(PaymentService payments, OrderRepository repo, String orderId) {
        this.payments = payments; this.repo = repo; this.orderId = orderId;
    }

    @Override
    public void execute() { txId = payments.payOrder(orderId); }

    @Override
    public void undo() {
        Order o = repo.get(orderId);
        if (o != null) { o.setPaid(false); repo.save(o); }
        txId = null;
    }

    public String getTxId() { return txId; }
}
