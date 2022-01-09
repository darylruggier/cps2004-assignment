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

        Order order2 = new Order(user1, btc, 10, Order.OrderType.SELL, Order.OrderSubType.MARKET);
        platform.addOrder(order2);

        //TODO: updateBook() ? or processOrders() here? (GOAL: MAKE MAIN MORE ABSTRACT)


    }
}