package service.checkout;

import domain.Order;
import invoice.InvoiceRenderer;
import notify.Notifier;
import repo.OrderRepository;
import service.PaymentService;
import service.PricingService;

public class CheckoutService implements CheckoutFacade {
    private final PricingService pricing;          // pricing
    private final PaymentService payments;         // payments
    private final Notifier notifier;               // notify system
    private final InvoiceRenderer invoiceRenderer; // invoice
    private final OrderRepository repo;            // repo

    public CheckoutService(PricingService pricing,
                           PaymentService payments,
                           Notifier notifier,
                           InvoiceRenderer invoiceRenderer,
                           OrderRepository repo) {
        this.pricing = pricing;
        this.payments = payments;
        this.notifier = notifier;
        this.invoiceRenderer = invoiceRenderer;
        this.repo = repo;
    }

    public String checkout(Order order) {
        pricing.price(order);
        repo.save(order);

        String txId = payments.payOrder(order.getId());

        String invoice = invoiceRenderer.render(order);
        notifier.notify(order.getCustomer().getEmail(), "Your Order Invoice", invoice);

        return txId;
    }
}
