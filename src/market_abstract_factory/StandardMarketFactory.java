package market_abstract_factory;

import invoice.*;
import notify.*;
import notify.decorators.LoggingNotifier;
import notify.decorators.PushNotifier;
import notify.decorators.SMSNotifier;
import payment.*;
import payment.proxy.SecurePaymentGateway;
import policy.*;
import policy_factory.*;
import repo.InMemoryOrderRepo;

public class StandardMarketFactory implements MarketFactory {
    private final DiscountFactory discountFactory;

    public StandardMarketFactory(DiscountFactory discountFactory) {
        this.discountFactory = discountFactory;
    }

    @Override
    public DiscountPolicy createDiscountPolicy() {
        return discountFactory.createPolicy();
    }

    @Override
    public PaymentGateway createPaymentGateway() {
        PaymentGateway real = new AmazonPayment();
        return new SecurePaymentGateway(real, InMemoryOrderRepo.getInstance());
    }

    @Override
    public Notifier createNotifier() {
        return new LoggingNotifier(new SMSNotifier(new PushNotifier(new EmailNotifier())));
    }

    @Override
    public InvoiceRenderer createInvoiceRenderer() {
        return new BridgeInvoiceRenderer(new SimpleTextInvoiceFormat());
        
    }
}
