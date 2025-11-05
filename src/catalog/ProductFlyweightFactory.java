package catalog;

import java.util.HashMap;
import java.util.Map;

public class ProductFlyweightFactory {
private final Map<String, ProductFlyweight> pool = new HashMap<>();

    public ProductFlyweight get(String sku, String name, double basePrice) {
        ProductFlyweight existing = pool.get(sku);
        if (existing != null) {
            return existing;
        }
        ProductFlyweight created = new ProductFlyweight(sku, name, basePrice);
        ProductFlyweight raced = pool.putIfAbsent(sku, created);
        return (raced == null) ? created : raced;
    }

    public int poolSize() {
        return pool.size();
    }
}
