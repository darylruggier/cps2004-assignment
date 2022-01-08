package order;

import java.math.BigDecimal;

public class Crypto {
    public int supply;
    public BigDecimal price;
    public String symbol;

    public Crypto(String symbol, BigDecimal price, int supply) { // Constructor. Will be used to create various coins if needed.
        this.symbol = symbol;
        this.price = price;
        this.supply = supply;
    }
}
