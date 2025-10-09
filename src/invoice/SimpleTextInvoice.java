package invoice;

import domain.Order;
import domain.OrderItem;

public class SimpleTextInvoice implements InvoiceRenderer {
    @Override
    public String render(Order order) {
        String result = "INVOICE #" + order.getId() + "\n" +
                "Customer: " + order.getCustomer().getEmail() + "\n" +
                "Items:\n";

        for (OrderItem it : order.getItems()) {
            result += String.format(" - %s x%d = %.2f\n",
                    it.getProduct().getName(),
                    it.getQty(),
                    it.getProduct().getPrice() * it.getQty());
        }

        result += String.format("Subtotal: %.2f\n", order.getSubtotal());
        result += String.format("Discount: %.2f\n", order.getDiscount());
        result += String.format("TOTAL: %.2f\n", order.getTotal());
        result += "PAID: " + order.isPaid() + "\n";

        return result;
    }
}
