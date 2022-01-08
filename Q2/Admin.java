package Q2;

import java.util.concurrent.atomic.AtomicInteger;

public class Admin extends User {
    private static final AtomicInteger count = new AtomicInteger(0); //Used to set IDs (index 1)
    private int admin_id;

    public Admin(String name, int age) { // Constructor
        super(name, age);
        this.admin_id = count.incrementAndGet(); // Incrementing the ID and assigning it to the Admin
    }

    public void approveUsers() { // Function used to approve the user, given they are age 18+
        for (int i = 0; i < registrations.size(); i++) { // how do I access this without making multiple instances of platform? interfaces for multiple inheritance? 
            if (registrations[i].age >= 18) {
                registrations[i].approved = true;
            }
            registrations[i].approved = false;
        }
    }
}
