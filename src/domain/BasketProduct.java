package domain;

import java.util.ArrayList;

public class BasketProduct extends Product {
    private ArrayList<Product> components;

    public BasketProduct(String sku, String name) {
        super(sku, name, 0.0);
        this.components = new ArrayList<>();
    }

    public void add(Product product) {
        if (product != null) {
            components.add(product);
            recalcPrice();
        }
    }

    public void remove(Product product) {
        components.remove(product);
        recalcPrice();
    }

    private void recalcPrice() {
        double sum = 0.0;
        for (Product p : components) {
            sum += p.getPrice();
        }
        super.setPrice(sum);
    }

    public ArrayList<Product> getComponents() {
        return components;
    }

    @Override
    public double getPrice() {
        double sum = 0.0;
        for (Product p : components) {
            sum += p.getPrice();
        }
        return sum;
    }
}
