//        Quiz quiz = new Quiz();
//
//
//        quiz.addQuestion(QuestionFactory.createMultipleChoiceQuestion(
//                "What is the capital of France?",
//                new String[]{"Berlin", "London", "Paris", "Madrid"},
//                2,
//                "It's also called the city of love.",
//                Question.Difficulty.EASY
//        ));
//
//        quiz.addQuestion(QuestionFactory.createTrueFalseQuestion(
//                "The Earth is flat.",
//                false,
//                "Think about gravity!",
//                Question.Difficulty.EASY
//        ));
//
//        quiz.addQuestion(QuestionFactory.createFillInTheBlankQuestion(
//                "What is the largest planet in our Solar System?",
//                "Jupiter",
//                "It is a gas giant.",
//                Question.Difficulty.MEDIUM
//        ));

import controller.QuizController;
import controller.UserController;
import service.Observer.ConsoleScoreObserver;
import model.Quiz;

import java.util.Scanner;

public class QuizApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserController userController = new UserController();
        Quiz quiz = new Quiz();
        QuizController quizController = new QuizController();
        Long userID = null;
        boolean isAdmin = false;

        while (true) {
            if (userID == null) {

                System.out.println("Please choose an option:\n1. Sign in\n2. Sign up\n3. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    userID = userController.loginUser();
                    if (userID != null) {
                        isAdmin = (userID == 0L);
                        System.out.println("Logged in successfully as " + (isAdmin ? "Admin" : "User") + ".");
                    } else {
                        System.out.println("Login failed. Please try again.");
                    }
                } else if (choice == 2) {
                    userController.registerUser();
                } else if (choice == 3) {
                    System.out.println("Exiting application.");
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("\nWelcome " + (isAdmin ? "Admin" : "User") + "! Please select an option:");
                System.out.println("1. Start Quiz");
                if (isAdmin) {
                    System.out.println("2. Manage Quiz Questions (Admin Only)");
                }
                System.out.println((isAdmin ? "3" : "2") + ". Logout");
                System.out.println((isAdmin ? "4" : "3") + ". Exit");

                int userChoice = scanner.nextInt();
                scanner.nextLine();

                if (userChoice == 1) {
                    startQuiz(quiz);
                } else if (isAdmin && userChoice == 2) {
                    manageQuiz(scanner, quiz);
                } else if (userChoice == (isAdmin ? 3 : 2)) {
                    System.out.println("Logging out...");
                    userID = null;
                    isAdmin = false;
                } else if (userChoice == (isAdmin ? 4 : 3)) {
                    System.out.println("Exiting application.");
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        scanner.close();
    }

    private static void startQuiz(Quiz quiz) {
        ConsoleScoreObserver scoreObserver = new ConsoleScoreObserver();
        quiz.addObserver(scoreObserver);
        QuizController quizController = new QuizController();
        quizController.start(quiz);
    }

    private static void manageQuiz(Scanner scanner, Quiz quiz) {
        while (true) {
            System.out.println("\nQuiz Management Menu:\n1. Add Question\n2. Edit Question\n3. Delete Question\n4. Display Quiz\n5. Go Back");
            int adminChoice = scanner.nextInt();
            scanner.nextLine();
            QuizController quizController = new QuizController();
            switch (adminChoice) {
                case 1 -> quizController.addQuestionToQuiz(scanner, quiz);
                case 2 -> quizController.editQuestionInQuiz(scanner, quiz);
                case 3 -> quizController.deleteQuestionFromQuiz(scanner, quiz);
                case 4 -> quizController.displayQuiz(quiz);
                case 5 -> {
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

}
