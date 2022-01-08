package com.q2;

import platform.Platform;
import user.Admin;
import user.User;

public class Main {

    public static void main(String[] args) {
        Platform platform = Platform.getInstance();

        User user1 = new User("Daryl", 15);
        User user2 = new User("Adam", 20);

        User admin = new Admin("Admin", 20);
    }
}