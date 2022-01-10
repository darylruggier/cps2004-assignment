package Q2.User;

import java.util.concurrent.atomic.AtomicInteger;

import Q2.Platform.Platform;

public class Admin extends User {
    Platform platform = Platform.getInstance();
    private static final AtomicInteger count = new AtomicInteger(0); //Used to set IDs (index 1)
    private int admin_id;

    public Admin(String name, int age) { // Constructor
        super(name, age);
        this.admin_id = count.incrementAndGet(); // Incrementing the ID and assigning it to the Admin
    }

    public void approveUsers() { // Function used to approve the user, given they are age 18+
        for (int i = 0; i < platform.registrations.size(); i++) { // how do I access this without making multiple instances of platform? interfaces for multiple inheritance? 
            if (platform.registrations[i].age >= 18) {
                platform.registrations[i].approved = true;
            }
            platform.registrations[i].approved = false;
        }
    }
}
