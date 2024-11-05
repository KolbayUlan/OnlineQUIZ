package controller.state;

import app.QuizApp;
import controller.facade.QuizFacade;

import java.util.Scanner;

public class UserState implements State {
    private final QuizApp quizApp;
    private final Scanner scanner;
    private final QuizFacade quizFacade;

    public UserState(QuizApp quizApp, Scanner scanner, QuizFacade quizFacade) {
        this.quizApp = quizApp;
        this.scanner = scanner;
        this.quizFacade = quizFacade;
    }

    @Override
    public void handle() {
        System.out.println("\nWelcome User! Please select an option:");
        System.out.println("1. Start Quiz\n2. Logout\n3. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> quizFacade.startQuiz();
            case 2 -> quizApp.setState(new LoginState(quizApp, scanner, quizFacade));
            case 3 -> System.exit(0);
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }
}
