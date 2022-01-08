package Q2;

import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private static final AtomicInteger count = new AtomicInteger(0); // Used to set IDs (index 1)
    private final int user_id;
    protected String name;
    protected int age;
    protected boolean approved = false;

    public User(String name, int age) { // Constructor
        this.name = name;
        this.age = age;
        this.user_id = count.incrementAndGet(); // Incrementing the ID and assigning it to the User
    }

    public void placeOrder(Order order) {
        if (!approved) {
            System.out.println("User is not an approved trader.");
        }
    }
    
    /*public int getID() { // Used for testing purposes
        return user_id;
    } */
}
