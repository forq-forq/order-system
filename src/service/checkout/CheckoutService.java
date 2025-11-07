package service.checkout;

import domain.Order;
import invoice.InvoiceRenderer;
import notify.Notifier;
import repo.OrderRepository;
import service.PaymentService;
import service.PricingService;
import service.chain.OrderHandler;

public class CheckoutService implements CheckoutFacade {
    private final PricingService pricing;
    private final PaymentService payments;
    private final Notifier notifier;
    private final InvoiceRenderer invoiceRenderer;
    private final OrderRepository repo;
    private final OrderHandler preCheckoutChain;

    public CheckoutService(PricingService pricing,
                        PaymentService payments,
                        Notifier notifier,
                        InvoiceRenderer invoiceRenderer,
                        OrderRepository repo,
                        OrderHandler preCheckoutChain) {
        this.pricing = pricing;
        this.payments = payments;
        this.notifier = notifier;
        this.invoiceRenderer = invoiceRenderer;
        this.repo = repo;
        this.preCheckoutChain = preCheckoutChain;
    }

    @Override
    public String checkout(Order order) {
        pricing.price(order);
        preCheckoutChain.handle(order);
        repo.save(order);
        String txId = payments.payOrder(order.getId());
        String invoice = invoiceRenderer.render(order);
        notifier.notify(order.getCustomer().getEmail(), "Your Order Invoice", invoice);
        return txId;
    }
}
