package market_abstract_factory;

import invoice.*;
import notify.*;
import payment.*;
import policy.*;

public interface MarketFactory {
    DiscountPolicy createDiscountPolicy();
    PaymentGateway createPaymentGateway();
    Notifier createNotifier();
    InvoiceRenderer createInvoiceRenderer();
}
