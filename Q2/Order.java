package Q2;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
	private long id;
	private OrderType orderType;
	private BigDecimal price;
	private long volume;
	private Date timestamp;
	private User user;

	public static final Order EMPTY = new Builder().build();

	public enum OrderType { // Type of order
		BUY, SELL;
	}
	
	// Creates deep copy of given order.
	public static Order copyOf(Order order) {
		return new Builder()
				.id(order.id)
				.orderType(order.orderType)
				.price(order.price)
				.volume(order.volume)
				.user(order.user)
				.build();
	}
	
	public static class Builder {
		private long id;
		
		private OrderType orderType;
		
		private BigDecimal price;
		
		private long volume;

		private User user;
		
		public Builder() {
		}
		
		public Order.Builder id(long id) {
			this.id = id;
			return this;
		}
		
		public Order.Builder orderType(OrderType orderType) {
			this.orderType = orderType;
			return this;
		}
		
		public Order.Builder price(BigDecimal price) {
			this.price = price;
			return this;
		}
		
		public Order.Builder volume(long volume) {
			this.volume = volume;
			return this;
		}
		
		public Order.Builder user(User user) {
			this.user = user;
			return this;
		}
		
		public Order build() {
			return new Order(this);
		}
	}
	
	private Order(Order.Builder builder) {
		this.id = builder.id;
		this.orderType = builder.orderType;
		this.price = builder.price;
		this.volume = builder.volume;
		
		timestamp = new Date();
	}

	public void decreaseVolume(long delta) {
		volume = getVolume() - delta;
	}

	@Override
	public String toString() {
		return String.format("[%s] %d; %f; %d", orderType.toString(), id, price, volume);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public long getId() {
		return id;
	}
	
	public OrderType getOrderType() {
		return orderType;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public long getVolume() {
		return volume;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}

	public User getUser() {
		return user;
	}
}
