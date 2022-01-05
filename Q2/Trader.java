package Q2;

import java.util.concurrent.atomic.AtomicInteger;

public class Trader extends User {
    private static final AtomicInteger count = new AtomicInteger(0); // Used to set IDs (index 1)
    private final int trader_id;
    boolean approved = false;

    public Trader(String name, int age) { // Constructor
        super(name, age);
        this.trader_id = count.incrementAndGet(); // Incrementing the ID and assigning it to the trader
    }



}
