package app;

import domain.*;
import market_abstract_factory.*;
import policy_factory.*;
import repo.InMemoryOrderRepo;
import service.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Customer cust = new Customer("c1", "240103174@sdu.edu.kz");

        Product p1 = new Product("SKU-1", "Keyboard", 50.0);
        Product p2 = new Product("SKU-2", "Mouse", 30.0);

        ArrayList<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem(p1, 2));
        items.add(new OrderItem(p2, 1));

        Order order = new Order("ord-1001", cust, items);

        // Initialize all the factories
        DiscountFactory discountFactory = new ThresholdDiscountFactory(100.0, 10.0);

        // Initialize the type of market
        MarketFactory factory = new StandardMarketFactory(discountFactory);

        InMemoryOrderRepo repo = new InMemoryOrderRepo();
        PricingService pricing = new PricingService(factory.createDiscountPolicy());
        PaymentService payments = new PaymentService(factory.createPaymentGateway(), repo);
        CheckoutService checkout = new CheckoutService(
                pricing, 
                payments, 
                factory.createNotifier(), 
                factory.createInvoiceRenderer(), 
                repo
        );

        String tx = checkout.checkout(order);
        System.out.println("\nTX = " + tx);
    }
}
