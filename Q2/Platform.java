package Q2;

import java.util.Vector;

public class Platform extends OrderBook { // singleton class
    private static Platform single_instance = null;

    OrderBook order_book;
    Vector<User> registrations;

    private Platform() {}  // Constructor

    public static Platform getInstance() { // Static method to create instance of the Platform class. If one is already created, it is returned.
        if (single_instance == null) {
            single_instance = new Platform();
        }
        return single_instance;
    }

    public void addOrder(Order order) { // Adding the order to the order history and the order queue
        order_book.orders.add(order);
        order_book.order_queue.add(order);
    }

    public Order getOrder() {
        return order_book.order_queue.pop();
    }

    public void registerUser(User user) {
        registrations.add(user);
    }
}
