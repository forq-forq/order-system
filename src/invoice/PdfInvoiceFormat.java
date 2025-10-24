package invoice;

import domain.Order;

public class PdfInvoiceFormat implements InvoiceFormat {
    @Override
    public String format(Order order) {
        return "[PDF] INVOICE #" + order.getId() + " | TOTAL: " + String.format("%.2f", order.getTotal());
    }
}
