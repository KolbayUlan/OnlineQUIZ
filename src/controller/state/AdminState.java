package controller.state;

import app.QuizApp;
import controller.facade.QuizFacade;

import java.util.Scanner;

public class AdminState implements State {
    private final QuizApp quizApp;
    private final Scanner scanner;
    private final QuizFacade quizFacade;

    public AdminState(QuizApp quizApp, Scanner scanner, QuizFacade quizFacade) {
        this.quizApp = quizApp;
        this.scanner = scanner;
        this.quizFacade = quizFacade;
    }

    @Override
    public void handle() {
        System.out.println("\nWelcome Admin! Please select an option:");
        System.out.println("1. Start Quiz\n2. Manage Quiz Questions\n3. Logout\n4. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> quizFacade.startQuiz();
            case 2 -> quizFacade.manageQuiz(scanner);
            case 3 -> quizApp.setState(new LoginState(quizApp, scanner, quizFacade));
            case 4 -> System.exit(0);
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }
}
