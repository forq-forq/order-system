package market_abstract_factory;

import invoice.*;
import notify.*;
import payment.*;
import policy.*;
import policy_factory.*;

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
        return new CommonPayment();
    }

    @Override
    public Notifier createNotifier() {
        return new EmailNotifier();
    }

    @Override
    public InvoiceRenderer createInvoiceRenderer() {
        return new BridgeInvoiceRenderer(new SimpleTextInvoiceFormat());
        
    }
}
