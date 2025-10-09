package repo;

import domain.Order;
import java.util.HashMap;
import java.util.Map;

public class InMemoryOrderRepo implements OrderRepository {
    private static final InMemoryOrderRepo INSTANCE = new InMemoryOrderRepo();
    private final Map<String, Order> store = new HashMap<>();  // map for storage

    private InMemoryOrderRepo() {}

    public static InMemoryOrderRepo getInstance() {
        return INSTANCE;
    }

    @Override
    public void save(Order order) {
        store.put(order.getId(), order);
    }

    @Override
    public Order get(String orderId) {
        return store.get(orderId);
    }
}
