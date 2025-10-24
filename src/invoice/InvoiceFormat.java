package invoice;

import domain.Order;

public interface InvoiceFormat {
    String format(Order order);
}
