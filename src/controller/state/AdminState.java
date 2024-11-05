package controller.state;

import app.QuizApp;
import controller.UserController;
import model.Quiz;
import controller.QuizController;

import java.util.Scanner;

public class AdminState implements State {
    private final QuizApp quizApp;
    private final Scanner scanner;
    private final QuizController quizController;

    public AdminState(QuizApp quizApp, Scanner scanner) {
        this.quizApp = quizApp;
        this.scanner = scanner;
        this.quizController = new QuizController();
    }

    @Override
    public void handle() {
        System.out.println("\nWelcome Admin! Please select an option:");
        System.out.println("1. Start Quiz\n2. Manage Quiz Questions\n3. Logout\n4. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> quizApp.setState(new QuizState(quizApp, quizApp.getQuiz()));
            case 2 -> manageQuiz();
            case 3 -> quizApp.setState(new LoginState(quizApp, scanner, new UserController()));
            case 4 -> {
                System.out.println("Exiting application.");
                System.exit(0);
            }
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private void manageQuiz() {
        while (true) {
            System.out.println("\nQuiz Management Menu:\n1. Add Question\n2. Edit Question\n3. Delete Question\n4. Display Quiz\n5. Go Back");
            int adminChoice = scanner.nextInt();
            scanner.nextLine();
            switch (adminChoice) {
                case 1 -> quizController.addQuestionToQuiz(scanner, quizApp.getQuiz());
                case 2 -> quizController.editQuestionInQuiz(scanner, quizApp.getQuiz());
                case 3 -> quizController.deleteQuestionFromQuiz(scanner, quizApp.getQuiz());
                case 4 -> quizController.displayQuiz(quizApp.getQuiz());
                case 5 -> { return; }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
