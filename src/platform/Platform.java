package platform;

import java.util.ArrayList;

import order.Order;
import order.OrderBook;
import user.User;

public class Platform { // singleton class
    private static Platform single_instance = null;

    public OrderBook order_book;
    public ArrayList<User> registrations;

    private Platform() {
    }

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

    public void approveUsers() { // Function used to approve the user, given they are age 18+
        for (User registration : registrations) { // how do I access this without making multiple instances of platform? interfaces for multiple inheritance?
            if (registration.age >= 18) {
                registration.approved = true;
            }
            registration.approved = false;
        }
    }

    public int getUserID(User user) {
        return user.user_id;
    }
}
