package invoice;

import domain.Order;

public class BridgeInvoiceRenderer implements InvoiceRenderer {
    private final InvoiceFormat format;

    public BridgeInvoiceRenderer(InvoiceFormat format) {
        this.format = format;
    }

    @Override
    public String render(Order order) {
        return format.format(order);
    }
}
