package controller.state;

import controller.UserController;
import app.QuizApp;

import java.util.Scanner;

public class LoginState implements State {
    private final QuizApp quizApp;
    private final Scanner scanner;
    private final UserController userController;

    public LoginState(QuizApp quizApp, Scanner scanner, UserController userController) {
        this.quizApp = quizApp;
        this.scanner = scanner;
        this.userController = userController;
    }

    @Override
    public void handle() {
        System.out.println("Please choose an option:\n1. Sign in\n2. Sign up\n3. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            Long userID = userController.loginUser();
            if (userID != null) {
                quizApp.setUserID(userID);
                quizApp.setState(userID == 0L ? new AdminState(quizApp, scanner) : new UserState(quizApp, scanner));
                System.out.println("Logged in successfully as " + (userID == 0L ? "Admin" : "User") + ".");
            } else {
                System.out.println("Login failed. Please try again.");
            }
        } else if (choice == 2) {
            userController.registerUser();
        } else if (choice == 3) {
            System.out.println("Exiting application.");
            System.exit(0);
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }
}
