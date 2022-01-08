package Q2;

public class Launcher {
    public static void main(String args[]) {
        Platform platform = Platform.getInstance();
        
        User user1 = new User("Daryl", 15);
        User user2 = new User("Adam", 20);

        User admin = new Admin("Admin", 20);

    }
}