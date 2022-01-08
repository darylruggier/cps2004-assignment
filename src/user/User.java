package user;

import order.Order;

import java.util.concurrent.atomic.AtomicInteger;
public class User {
    private static final AtomicInteger count = new AtomicInteger(0); // Used to set IDs (index 1)
    public final int user_id;
    protected String name;
    public int age;
    public boolean approved = false;
    public boolean isAdmin = false;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
        this.user_id = count.incrementAndGet(); // Incrementing the ID and assigning it to the User
    }

    public void placeOrder(Order order) {
        if (!approved) {
            System.out.println("User is not an approved trader.");
        }
    }
}