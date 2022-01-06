package Q2;
import java.util.concurrent.BlockingQueue;
import java.util.*;

import Q2.Order.OrderType;

public class OrderBook {
    String id;
    List<Order> buys;
    List<Order> sells;

    HashMap<Long, Order> ordersCache = new HashMap<Long, Order>();
    BlockingQueue<Order> order_queue;  // data structure to hold orders

    public OrderBook(String id, BlockingQueue<Order> order_queue) { // Constructor
        this.order_queue = order_queue;
        this.id = id;

        buys = new LinkedList<Order>();
        sells = new LinkedList<Order>();
    }

    public void run() {
        while (true) { // Keeps attempting to take orders off the queue while possible.
            try {
                Order order = order_queue.take();
                if (order.getUser().approved) {
                    if (OrderType.BUY == order.getOrderType()) {
                        buy(order);
                    } else if (OrderType.SELL == order.getOrderType()) {
                        sell(order);
                    }
                } else { // Exits if user is not approved by an Admin
                    System.out.println("User not approved.");
                    System.exit(0);
                }
            } catch (InterruptedException e) {
                // Stops when interrupted.
            }
        }
    }

    private void buy(Order order) {
        if (order == null || order.getOrderType() != OrderType.BUY) {
            System.out.println("Error: Order is NULL, or order type is not buy.");
            System.exit(0);
        }

        processBuy(order);

        if (order.getVolume() > 0) {
            ordersCache.put(order.getId(), order);
            
            buys.add(order);
            Collections.sort(buys, new Comparator<Order>() {
                @Override
                public int compare(Order first, Order second) {
                    if (first.getPrice().equals(second.getPrice())) {
                        return first.getTimestamp().compareTo(second.getTimestamp());
                    } else {
                        return -1 * first.getPrice().compareTo(second.getPrice());
                    }
                }
            });
        }
    }

    private void sell(Order order) {
        if (order == null || order.getOrderType() != OrderType.SELL) {
            System.out.println("Error: Order is NULL, or order type is not sell.");
            System.exit(0);
        }
        
        processSell(order);
        
        if (order.getVolume() > 0) {
            ordersCache.put(order.getId(), order);
            
            sells.add(order);
            
            Collections.sort(sells, new Comparator<Order>() {
                @Override
                public int compare(Order first, Order second) {
                    if (first.getPrice().equals(second.getPrice())) {
                        return first.getTimestamp().compareTo(second.getTimestamp());
                    } else {
                        return first.getPrice().compareTo(second.getPrice());
                    }
                }
            });
        }
    }

    /** 
     * Tries to match given sell operation with all present buy operations. 
     * Gets rid of empty buy operations. 
     */
    private void processSell(Order sell) {
        for (Order buy : buys) {
            if (buy.getPrice().compareTo(sell.getPrice()) < 0 
                    || sell.getVolume() == 0) {
                break;
            }
            
            long contractVolume = Math.min(sell.getVolume(), buy.getVolume());
            sell.decreaseVolume(contractVolume);
            buy.decreaseVolume(contractVolume);
        }
        
        buys = clearEmptyOrders(buys);
    }

    // Tries to match given sell operation with all present buy operations (gets rid of empty buy operations)
    private void processBuy(Order buy) {
        for (Order sell : sells) {
            if (sell.getPrice().compareTo(buy.getPrice()) > 0 
                    || buy.getVolume() == 0) {
                break;
            }
            
            long contractVolume = Math.min(buy.getVolume(), sell.getVolume());
            buy.decreaseVolume(contractVolume);
            sell.decreaseVolume(contractVolume);
        }
        
        sells = clearEmptyOrders(sells);
    }

    List<Order> clearEmptyOrders(List<Order> orders) {
        int index = 0;
        
        for (Order o : orders) {
            if (o.getVolume() > 0L) {
                break;
            }
             
            index++;
            this.ordersCache.remove(o.getId());
        }
        
        int lastIndex = Math.max(0, orders.size());

        List<Order> updatedOrders = new ArrayList<Order>();
        //newArrayList(orders.subList(Math.min(index, lastIndex), lastIndex));
        return updatedOrders;
    }

    /** Removes order with given identifier from the book. */
    public void remove(Long orderId) {
        if (ordersCache.containsKey(orderId)) {
            Order toBeRemoved = ordersCache.remove(orderId);
            if (Order.OrderType.BUY == toBeRemoved.getOrderType()) {
                buys.remove(toBeRemoved);
            } else if (Order.OrderType.SELL == toBeRemoved.getOrderType()){
                sells.remove(toBeRemoved);
            }
        }
    }

    // Returns String representation of order from the list.
    private String getFormattedOrder(List<Order> orders, int index) {
        String formattedOrder = "";
        if (index >= 0 && index < orders.size()) {
            Order order =  orders.get(index);
            formattedOrder = String.format("%d@%.2f", order.getVolume(), order.getPrice());
        }
        return formattedOrder; 
    }

    public List<Order> getBuys() {
        return buys;
    }

    public List<Order> getSells() {
        return sells;
    }

    public Order getOrder(Long orderId) {
        return ordersCache.get(orderId);
    }


    // These final 3 functions have been imported from the Guava Lists library.
    public static <E> ArrayList<E> newArrayList(E... elements) {
        int capacity = computeArrayListCapacity(elements.length);
        ArrayList<E> list = new ArrayList<E>(capacity);
        Collections.addAll(list, elements);
        return list;
    }
    

    static int computeArrayListCapacity(int arraySize) {
        if (arraySize <= 0) {
            return 0;  // arraySize < 0, error
        }
        return saturatedCast(5L + arraySize + (arraySize / 10));
    }

    public static int saturatedCast(long value) {
        if (value > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (value < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) value;
    }
}
