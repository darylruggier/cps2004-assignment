package com.q2;

import platform.Platform;
import order.Crypto;
import order.Order;
import user.Admin;
import user.User;

public class Main {
    public static void main(String[] args) {
        Platform platform = Platform.getInstance();

        Admin admin1 = new Admin("Admin", 20);
        User user1 = new User("Daryl", 18, 2000);
        platform.registerUser(user1);
        admin1.processRegistrations();

        Crypto btc = new Crypto("btc", 20, 200);

        Order order1 = new Order(user1, btc, 20, Order.OrderType.BUY, Order.OrderSubType.MARKET);
        platform.addOrder(order1);

        Order order2 = new Order(user1, btc, 20, Order.OrderType.BUY, Order.OrderSubType.MARKET);
        platform.addOrder(order2);

        Order order3 = new Order(user1, btc, 20, Order.OrderType.SELL, Order.OrderSubType.LIMIT, 1.2);
        platform.addOrder(order3);

        Order order4 = new Order(user1, btc, 10, Order.OrderType.SELL, Order.OrderSubType.MARKET);
        platform.addOrder(order4);

        //TODO: Implement further Abstraction and Modularity.
        for (Order orders : platform.order_book.order_queue) {
            System.out.println("Order ID: " + orders.order_id + "\nMade by user: " + orders.user + "\nOrder Type: " + orders.orderType + "\nOrder Sub-type: " + orders.orderSubType);
            platform.processOrder(orders);
        }
        //ISSUE: The issue with processOrder() in the above for loop is the deletion of orders after being filled.
        //This causes a ConcurrencyModification Exception. (this issue arose when changing order_queue from a LinkedList to a Queue.)
        //Either do the following or go back and try to traverse the LinkedList in reverse.
        //Also,the deletion of orders has been moved from the switch statement in processOrder() to updateOrderQueue().
        //I believe that this function should be run as a background process (i.e. concurrency using threads & Runnable).
    }
}