package platform;

import java.util.ArrayList;

import order.Order;
import order.OrderBook;
import user.User;

public class Platform { // singleton class
    private static Platform single_instance = null;

    public OrderBook order_book = new OrderBook();
    public ArrayList<User> registrations = new ArrayList<User>();

    private Platform() {
    }

    public static Platform getInstance() { // Static method to create instance of the Platform class. If one is already created, it is returned.
        if (single_instance == null) {
            single_instance = new Platform();
        }
        return single_instance;
    }

    public int count = 0;
    public void addOrder(Order order) { // Adding the order to the order history and the order queue
        order_book.orders.add(order);
        order_book.order_queue.push(order);
        count++;
    }

    public Order getOrder() {
        return order_book.order_queue.pop();
    }

    public void processOrder(Order order) {
        switch(order.orderType) {
            case BUY:
                switch(order.orderSubType) {
                    case MARKET:
                        if (order.quantity > order.crypto.supply && order.crypto.supply > 0) { // partially filled
                            double filled_quantity = order.quantity - order.crypto.supply;
                            order.quantity -= order.crypto.supply; // partially filled.
                            order.user.fiat_balance -= (filled_quantity * order.crypto.price);
                            order.user.crypto_balance += filled_quantity;
                            order.quantity -= filled_quantity; //updating order quantity
                            System.out.println("Order (ID " + order.order_id + ") has been partially filled.");
                        } else if (order.crypto.supply > order.quantity) { // filled
                            order.crypto.supply -= order.quantity; // filled
                            order.user.crypto_balance += order.quantity;
                            order.user.fiat_balance -= order.quantity * order.crypto.price;
                            order_book.order_queue.remove(order); // updating order book
                            System.out.println("Order (ID " + order.order_id + ") has been filled.");
                        } else {
                            System.out.println("Invalid order.");
                        }
                        break;

                    case LIMIT:
                        // check quantity is valid (i.e. order.quantity > 0)
                        // if (ask.price >= crypto.price (current price)
                            // if order.quantity > crypto.supply && crypto.supply > 0 // partial fill
                                // int filled_quantity = order.quantity - crypto.supply;
                                // user.fiat_balance -= (filled.quantity * ask.price)
                                // user.crypto_balance += filled_quantity;
                                // order.quantity -= filled_quantity; // updating order_quantity
                                // System.out.println("Order (ID " + order.order_id + ") has been partially filled.");
                            // else if crypto.supply > order.quantity // filled
                                // crypto.supply -= order.quantity;
                                // user.crypto_balance += order.quantity;
                                // user.fiat_balance -= order.quantity * ask_price
                                // order_queue.remove(order); // remove order off queue as it is settled
                                // System.out.println("Order (ID " + order.order_id + ") has been filled.");


                }
            case SELL:
        }
    }

    public void registerUser(User user) {
        registrations.add(user);
    }

    public int getUserID(User user) {
        return user.user_id;
    }
}
