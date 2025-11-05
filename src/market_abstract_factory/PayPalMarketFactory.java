package market_abstract_factory;

import invoice.*;
import notify.*;
import payment.*;
import payment.proxy.*;
import policy.*;
import policy_factory.DiscountFactory;
import repo.InMemoryOrderRepo;

public class PayPalMarketFactory implements MarketFactory {
    private final DiscountFactory discountFactory;

    public PayPalMarketFactory(DiscountFactory discountFactory) {
        this.discountFactory = discountFactory;
    }

    @Override
    public DiscountPolicy createDiscountPolicy() {
        return discountFactory.createPolicy();
    }

    @Override
    public PaymentGateway createPaymentGateway() {
        PaymentGateway real = new PayPalAdapter(new PayPalPayment());
        return new SecurePaymentGateway(real, InMemoryOrderRepo.getInstance());
    }

    @Override
    public Notifier createNotifier() {
        return new EmailNotifier();
    }

    @Override
    public InvoiceRenderer createInvoiceRenderer() {
        return new BridgeInvoiceRenderer(new PdfInvoiceFormat());
    }
}
