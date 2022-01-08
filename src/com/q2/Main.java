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
        admin1.approveUsers();

        Crypto btc = new Crypto("btc", 20, 200);
        Order order1 = new Order(user1, btc, 2, Order.OrderType.BUY, Order.OrderSubType.MARKET);
        System.out.println("User: " + user1.name + " has FIAT balance " + user1.fiat_balance);
        System.out.println("User: " + user1.name + " has CRYPTO balance " + user1.crypto_balance);
        platform.addOrder(order1);
        platform.processOrder(order1);
        System.out.println("User: " + user1.name + " has FIAT balance " + user1.fiat_balance);
        System.out.println("User: " + user1.name + " has CRYPTO balance " + user1.crypto_balance);
    }
}