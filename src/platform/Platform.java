package platform;

import java.util.ArrayList;

import order.Order;
import order.OrderBook;
import user.User;

public class Platform { // singleton class
    private static Platform single_instance = null;

    public OrderBook order_book = new OrderBook();
    public ArrayList<User> registrations = new ArrayList<>();

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
        if (order.quantity <= 0) {
            System.out.println("Invalid order quantity.");
            return;
        }
        order_book.orders.add(order);
        order_book.order_queue.push(order);
        count++;
    }

    public Order getOrder() {
        return order_book.order_queue.pop();
    }

    public void processOrder(Order order) {
        String order_filled_msg = order.orderType + " " + order.orderSubType + " Order (ID " + order.order_id + ") has been filled.";
        String order_partially_filled_msg = order.orderType + " " + order.orderSubType + " Order (ID " + order.order_id + ") has been partially filled.";
        switch(order.orderType) {
            case BUY:
                switch(order.orderSubType) {
                    case MARKET:
                        if (order.quantity > order.crypto.supply && order.crypto.supply > 0) { // partially filled
                            double filled_quantity = order.quantity - order.crypto.supply;
                            order.quantity -= order.crypto.supply; // partially filled.
                            order.crypto.supply -= filled_quantity;
                            order.user.fiat_balance -= (filled_quantity * order.crypto.price);
                            order.user.crypto_balance += filled_quantity;
                            order.quantity -= filled_quantity; //updating order quantity
                            System.out.println(order_partially_filled_msg);
                        } else if (order.crypto.supply > order.quantity) { // filled
                            order.crypto.supply -= order.quantity;
                            order.user.crypto_balance += order.quantity;
                            order.user.fiat_balance -= order.quantity * order.crypto.price;
                            order.quantity = 0;
                            order_book.order_queue.remove(order); // updating order book
                            System.out.println(order_filled_msg);
                        } else {
                            System.out.println("Invalid order.");
                        }
                        break;

                    case LIMIT:
                        if (order.price <= order.crypto.price) {
                            if (order.quantity > order.crypto.supply && order.crypto.supply > 0) { // partial fill
                                double filled_quantity = order.quantity - order.crypto.supply;
                                order.crypto.supply -= filled_quantity;
                                order.user.fiat_balance -= (filled_quantity * order.price);
                                order.user.crypto_balance += filled_quantity;
                                order.quantity -= filled_quantity; // updating order_quantity
                                System.out.println(order_partially_filled_msg);
                            } else if (order.crypto.supply > order.quantity) { // filled
                                order.crypto.supply -= order.quantity;
                                order.user.crypto_balance += order.quantity;
                                order.user.fiat_balance -= order.quantity * order.price;
                                order.quantity = 0;
                                order_book.order_queue.remove(order); // remove order off queue as it is settled
                                System.out.println(order_filled_msg);                            }
                        } else {
                            //limit order could not be filled as crypto price is still above ask price.
                        }
                        break;
                    default:
                        System.out.println("Invalid order sub-type: must be MARKET or LIMIT order.");
                }
            case SELL:
                if (order.quantity > order.user.crypto_balance) {
                    System.out.println("Error: You are attempting to sell more than you own.");
                    return;
                }

                switch(order.orderSubType) {
                    case MARKET:
                        order.user.crypto_balance -= order.quantity;
                        order.user.fiat_balance += (order.quantity * order.crypto.price);
                        order.crypto.supply += order.quantity;
                        order.quantity = 0;
                        order_book.order_queue.remove(order);
                        System.out.println(order_filled_msg);
                        break;
                    case LIMIT:
                        if (order.price <= order.crypto.price) {
                            order.user.crypto_balance -= order.quantity;
                            order.user.fiat_balance += (order.quantity * order.price);
                            order.crypto.supply += order.quantity;
                            order.quantity = 0;
                            order_book.order_queue.remove(order);
                            System.out.println(order_filled_msg);
                        }
                        break;

                }
        }
    }

    public void registerUser(User user) {
        registrations.add(user);
    }

    public int getUserID(User user) {
        return user.user_id;
    }
}
