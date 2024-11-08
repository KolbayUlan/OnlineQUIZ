package controller.facade;

import controller.QuizController;
import controller.UserController;
import model.Quiz;
import service.Observer.ConsoleScoreObserver;

import java.util.Scanner;

public class QuizFacade {
    private final UserController userController;
    private final QuizController quizController;
    private final Quiz quiz;
    private final ConsoleScoreObserver scoreObserver;

    public QuizFacade() {
        this.userController = new UserController();
        this.quizController = new QuizController();
        this.quiz = new Quiz();
        this.scoreObserver = new ConsoleScoreObserver();
        quiz.addObserver(scoreObserver); // Attach observer to the quiz
    }

    public Long loginUser(Scanner scanner) {
        return userController.loginUser();
    }

    public void registerUser(Scanner scanner) {
        userController.registerUser();
    }

    public void logoutUser() {
        System.out.println("Logging out...");
    }

    public void startQuiz() {
        quizController.start(quiz);
    }

    public void manageQuiz(Scanner scanner) {
        while (true) {
            System.out.println("\nQuiz Management Menu:\n1. Add Question\n2. Edit Question\n3. Delete Question\n4. Display Quiz\n5. Go Back");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> quizController.addQuestionToQuiz(scanner, quiz);
                case 2 -> quizController.editQuestionInQuiz(scanner, quiz);
                case 3 -> quizController.deleteQuestionFromQuiz(scanner, quiz);
                case 4 -> quizController.displayQuiz(quiz);
                case 5 -> { return; }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public Quiz getQuiz() {
        return quiz;
    }
}

