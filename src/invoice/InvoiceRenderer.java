package invoice;

import domain.Order;

public interface InvoiceRenderer {
    String render(Order order); // render invoice text
}
