package order;

import user.User;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class Order {
    private static final AtomicInteger count = new AtomicInteger(0); // Used to set IDs (index 1)
    public final int order_id;
    public final OrderType orderType;
    public final OrderSubType orderSubType;
    public BigDecimal price;
    public double quantity;
    public long volume;
    public final User user;
    public final Crypto crypto;

    public enum OrderType { // Type of order
        BUY, SELL
    }

    public enum OrderSubType { // Order sub-type; i.e. market / limit
        MARKET, LIMIT
    }

    public Order(User user, Crypto crypto, double quantity, OrderType orderType, OrderSubType orderSubType, BigDecimal price) { // Constructor for LIMIT order (buys / sells at price indicated in the order)
        this.user = user;
        this.crypto = crypto;
        this.quantity = quantity;
        this.orderType = orderType;
        this.orderSubType = orderSubType;
        this.price = price;
        this.order_id = count.incrementAndGet();
    }

    public Order(User user, Crypto crypto, double quantity, OrderType orderType, OrderSubType orderSubType) { // Constructor for MARKET orders (buys / sells at current market price)
        this.user = user;
        this.crypto = crypto;
        this.quantity = quantity;
        this.orderType = orderType;
        this.orderSubType = orderSubType;
        this.order_id = count.incrementAndGet();
    }
}
