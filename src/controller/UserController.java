package controller;

import model.User;
import service.UserService;

import java.util.Scanner;

public class UserController {
    private final UserService userService = new UserService();

    public void registerUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        User user = new User(username, password, email);
        if (userService.registerUser(user)) {
            System.out.println("User registered successfully!");
        } else {
            System.out.println("Registration failed.");
        }
    }

    public Long loginUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userService.isAdmin(username, password)) {
            System.out.println("Admin logged in successfully!");
            return 0L;
        } else if (userService.loginUser(username, password)) {
            System.out.println("User logged in successfully!");
            return userService.userId(username);
        } else {
            System.out.println("Invalid credentials.");
        }
        return null;
    }
}
