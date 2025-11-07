package command;

import domain.Order;

public class ApplyManualDiscountCommand implements Command {
    private final Order order;
    private final double delta;
    private double prevDiscount;

    public ApplyManualDiscountCommand(Order order, double delta) {
        this.order = order; this.delta = delta;
    }

    @Override
    public void execute() {
        prevDiscount = order.getDiscount();
        order.setDiscount(prevDiscount + delta);
        order.setTotal(Math.max(0, order.getSubtotal() - order.getDiscount()));
    }

    @Override
    public void undo() {
        order.setDiscount(prevDiscount);
        order.setTotal(Math.max(0, order.getSubtotal() - order.getDiscount()));
    }
}
