package market_abstract_factory;

import invoice.*;
import notify.*;
import payment.*;
import policy.*;

public class PayPalMarketFactory implements MarketFactory {
    @Override
    public DiscountPolicy createDiscountPolicy() {
        return new NoDiscount();
    }

    @Override
    public PaymentGateway createPaymentGateway() {
        return new PayPalAdapter(new PayPalPayment());
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
