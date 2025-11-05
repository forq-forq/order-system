package domain;

import catalog.ProductFlyweight;

public class FlyweightBackedProduct extends Product {
    private final ProductFlyweight sharedData;

    public FlyweightBackedProduct(ProductFlyweight sharedData) {
        super(sharedData.getSku(), sharedData.getName(), sharedData.getBasePrice());
        this.sharedData = sharedData;
    }

    public ProductFlyweight getSharedData() {
        return sharedData;
    }
}
