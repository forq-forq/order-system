package invoice;

import domain.Order;
import domain.OrderItem;

public class SimpleTextInvoiceFormat implements InvoiceFormat {
    @Override
    public String format(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("INVOICE #").append(order.getId()).append("\n");
        sb.append("Customer: ").append(order.getCustomer().getEmail()).append("\n");
        sb.append("Items:\n");
        for (OrderItem it : order.getItems()) {
            sb.append(String.format(" - %s x%d = %.2f\n",
                    it.getProduct().getName(),
                    it.getQty(),
                    it.getProduct().getPrice() * it.getQty()));
        }
        sb.append(String.format("Subtotal: %.2f\n", order.getSubtotal()));
        sb.append(String.format("Discount: %.2f\n", order.getDiscount()));
        sb.append(String.format("TOTAL: %.2f\n", order.getTotal()));
        sb.append("PAID: ").append(order.isPaid()).append("\n");
        return sb.toString();
    }
}
