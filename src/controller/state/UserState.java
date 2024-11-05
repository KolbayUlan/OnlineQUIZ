package controller.state;

import app.QuizApp;
import controller.UserController;
import controller.state.LoginState;
import controller.state.QuizState;
import model.Quiz;

import java.util.Scanner;

public class UserState implements State {
    private final QuizApp quizApp;
    private final Scanner scanner;

    public UserState(QuizApp quizApp, Scanner scanner) {
        this.quizApp = quizApp;
        this.scanner = scanner;
    }

    @Override
    public void handle() {
        System.out.println("\nWelcome User! Please select an option:");
        System.out.println("1. Start Quiz\n2. Logout\n3. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> quizApp.setState(new QuizState(quizApp, quizApp.getQuiz())); // Start the shared quiz
            case 2 -> quizApp.setState(new LoginState(quizApp, scanner, new UserController()));
            case 3 -> {
                System.out.println("Exiting application.");
                System.exit(0);
            }
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }
}
