package command;

import domain.Order;
import domain.OrderItem;

public class RemoveItemCommand implements Command {
    private final Order order;
    private final String sku;
    private OrderItem removed;

    public RemoveItemCommand(Order order, String sku) {
        this.order = order; this.sku = sku;
    }

    @Override
    public void execute() { removed = order.removeItem(sku); }

    @Override
    public void undo() {
        if (removed != null) order.addItem(removed);
    }
}
