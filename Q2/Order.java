package Q2;
import java.math.BigDecimal;;

public class Order {
	private OrderType orderType;
    private OrderSubType orderSubType;
	private BigDecimal price;
    private double quantity;
	private long volume;
	private User user;
	private Crypto crypto;

	public enum OrderType { // Type of order
		BUY, SELL;
	}

    public enum OrderSubType { // Order sub-type; i.e. market / limit
        MARKET, LIMIT;
    }

    public Order(User user, Crypto crypto, double quantity, OrderType orderType, OrderSubType orderSubType, BigDecimal price) { // Constructor for LIMIT order (buys / sells at price indicated in the order)
        this.user = user;
        this.crypto = crypto;
        this.quantity = quantity;
        this.orderType = orderType;
        this.orderSubType = orderSubType;
        this.price = price;
    }

    public Order(User user, Crypto crypto, double quantity, OrderType orderType, OrderSubType orderSubType) { // Constructor for MARKET orders (buys / sells at current market price)
        this.user = user;
        this.crypto = crypto;
        this.quantity = quantity;
        this.orderType = orderType;
        this.orderSubType = orderSubType;
    }
}
