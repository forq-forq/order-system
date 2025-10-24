package app;

import domain.*;
import market_abstract_factory.*;
import policy_factory.*;
import repo.InMemoryOrderRepo;
import service.*;
import domain.builder.OrderBuilder;

public class Main {
    public static void main(String[] args) {
        Customer cust = new Customer("c1", "240103174@sdu.edu.kz");

        Product p1 = new Product("SKU-1", "Keyboard", 50.0);
        Product p2 = new Product("SKU-2", "Mouse", 30.0);
        Product p3 = p1.clonePrototype();
        p3.setSku("SKU-3"); p3.setPrice(60.0);
        
        Order order = new OrderBuilder()
                .id("ord-1001")
                .forCustomer(cust)
                .addItem(p1, 2)
                .addItem(p2, 1)
                .addItem(p3, 3)
                .build();
        /* 
        // Initialize all the factories
        DiscountFactory discountFactory = new ThresholdDiscountFactory(100.0, 10.0);

        // Initialize the type of market
        MarketFactory factory = new StandardMarketFactory(discountFactory);
        */

        MarketFactory factory = new PayPalMarketFactory();
        
        InMemoryOrderRepo repo = InMemoryOrderRepo.getInstance();
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
