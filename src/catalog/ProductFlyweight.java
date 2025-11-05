package catalog;

public final class ProductFlyweight {
    private final String sku;
    private final String name;
    private final double basePrice;

    public ProductFlyweight(String sku, String name, double basePrice) {
        this.sku = sku;
        this.name = name;
        this.basePrice = basePrice;
    }

    public String getSku() { return sku; }
    public String getName() { return name; }
    public double getBasePrice() { return basePrice; }
}
