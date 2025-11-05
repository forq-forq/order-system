package app;

import catalog.ProductFlyweightFactory;
import domain.*;
import market_abstract_factory.*;
import policy_factory.*;
import repo.InMemoryOrderRepo;
import service.*;
import service.checkout.CheckoutService;
import domain.builder.OrderBuilder;

public class Main {
    public static void main(String[] args) {
        Customer cust = new Customer("c1", "240103174@sdu.edu.kz");

        ProductFlyweightFactory flyweightFactory = new ProductFlyweightFactory();

        FlyweightBackedProduct p1 = new FlyweightBackedProduct(flyweightFactory.get("SKU-001", "Laptop", 1200.0));
        FlyweightBackedProduct p2 = new FlyweightBackedProduct(flyweightFactory.get("SKU-001", "Laptop", 1200.0));
        FlyweightBackedProduct p3 = new FlyweightBackedProduct(flyweightFactory.get("SKU-002", "Mouse", 30.0));

        OrderItem item1 = new OrderItem(p1, 1);
        OrderItem item2 = new OrderItem(p2, 2);
        OrderItem item3 = new OrderItem(p3, 1);

        BasketProduct a3 = new BasketProduct("SKU-5", "Laptop and Mouse");
        a3.add(p1);
        a3.add(p3);

        p3.setSku("SKU-6"); p3.setPrice(60.0);
        
        Order order = new OrderBuilder()
                .id("ord-1001")
                .forCustomer(cust)
                .addItem(item1)
                .addItem(item2)
                .addItem(item3)
                .addItem(a3, 1)
                .build();
        
        // Initialize all the factories
        DiscountFactory discountFactory = new NoDiscountFactory();

        // Initialize the type of market
        MarketFactory factory = new PayPalMarketFactory(discountFactory);
        
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
