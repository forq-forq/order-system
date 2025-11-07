package app;

import catalog.ProductFlyweightFactory;
import command.*;
import domain.*;
import domain.builder.OrderBuilder;
import market_abstract_factory.*;
import policy_factory.*;
import repo.InMemoryOrderRepo;
import service.*;
import service.checkout.CheckoutService;
import service.chain.*;

public class Main {
    public static void main(String[] args) {
        // initializing customer
        Customer customer = new Customer("C1", "customer@example.com");

        // initializing services
        ProductFlyweightFactory factory = new ProductFlyweightFactory();
        MarketFactory market = new StandardMarketFactory(new ThresholdDiscountFactory(100, 10));
        PricingService pricing = new PricingService(market.createDiscountPolicy());
        var repo = InMemoryOrderRepo.getInstance();
        PaymentService payment = new PaymentService(market.createPaymentGateway(), repo);

        // creating new products
        Product keyboard = new FlyweightBackedProduct(factory.get("SKU-1", "Keyboard", 50));
        Product mouse = new FlyweightBackedProduct(factory.get("SKU-2", "Mouse", 20));

        // creating new order
        Order order = new OrderBuilder().withId("ORD-1").withCustomer(customer).build();

        // run invoker
        CommandInvoker invoker = new CommandInvoker();
        invoker.run(new AddItemCommand(order, new OrderItem(keyboard, 1)));
        invoker.run(new AddItemCommand(order, new OrderItem(mouse, 2)));

        // creating chain
        OrderHandler chain = new ValidateOrderBasicsHandler();
        chain.setNext(new CheckPositivePricesHandler())
             .setNext(new SimpleFraudCheckHandler());

        // running last checkout service
        CheckoutService checkout = new CheckoutService(
                pricing,
                payment,
                market.createNotifier(),
                market.createInvoiceRenderer(),
                repo,
                chain
        );

        // 
        String tx = checkout.checkout(order);
        System.out.println("Transaction ID: " + tx);
    }
}
