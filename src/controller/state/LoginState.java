package controller.state;

import app.QuizApp;
import controller.facade.QuizFacade;

import java.util.Scanner;

public class LoginState implements State {
    private final QuizApp quizApp;
    private final Scanner scanner;
    private final QuizFacade quizFacade;

    public LoginState(QuizApp quizApp, Scanner scanner, QuizFacade quizFacade) {
        this.quizApp = quizApp;
        this.scanner = scanner;
        this.quizFacade = quizFacade;
    }

    @Override
    public void handle() {
        System.out.println("Please choose an option:\n1. Sign in\n2. Sign up\n3. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            Long userID = quizFacade.loginUser(scanner);
            if (userID != null) {
                quizApp.setUserID(userID);
                quizApp.setState(userID == 0L ? new AdminState(quizApp, scanner, quizFacade) : new UserState(quizApp, scanner, quizFacade));
                System.out.println("Logged in successfully as " + (userID == 0L ? "Admin" : "User") + ".");
            } else {
                System.out.println("Login failed. Please try again.");
            }
        } else if (choice == 2) {
            quizFacade.registerUser(scanner);
        } else if (choice == 3) {
            System.out.println("Exiting application.");
            System.exit(0);
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }
}
