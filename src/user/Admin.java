package user;

import java.util.concurrent.atomic.AtomicInteger;

public class Admin extends User {
    private static final AtomicInteger count = new AtomicInteger(0); //Used to set IDs (index 1)
    public final int admin_id;

    public Admin(String name, int age) {
        super(name, age);
        super.isAdmin = true;
        this.admin_id = count.incrementAndGet(); // Incrementing the ID and assigning it to the Admin
    }
}
