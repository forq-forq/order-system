package command;

import domain.Order;
import domain.OrderItem;

public class AddItemCommand implements Command {
    private final Order order;
    private final OrderItem item;

    public AddItemCommand(Order order, OrderItem item) {
        this.order = order; this.item = item;
    }

    @Override
    public void execute() { order.addItem(item); }

    @Override
    public void undo() { order.removeItem(item.getProduct().getSku()); }
}
