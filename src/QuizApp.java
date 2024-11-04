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

import controller.UserController;
import model.Question;
import model.QuestionFactory;
import service.Observer.ConsoleScoreObserver;
import controller.Quiz;

import java.util.Scanner;

public class QuizApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserController userController = new UserController();
        Quiz quiz = new Quiz();

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
        quiz.start();
    }

    private static void manageQuiz(Scanner scanner, Quiz quiz) {
        while (true) {
            System.out.println("\nQuiz Management Menu:\n1. Add Question\n2. Edit Question\n3. Delete Question\n4. Display Quiz\n5. Go Back");
            int adminChoice = scanner.nextInt();
            scanner.nextLine();

            switch (adminChoice) {
                case 1 -> addQuestionToQuiz(scanner, quiz);
                case 2 -> editQuestionInQuiz(scanner, quiz);
                case 3 -> deleteQuestionFromQuiz(scanner, quiz);
                case 4 -> quiz.displayQuiz();
                case 5 -> {
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addQuestionToQuiz(Scanner scanner, Quiz quiz) {
        System.out.println("Choose question type:\n1. Multiple Choice\n2. True/False\n3. Fill-in-the-Blank");
        int type = scanner.nextInt();
        scanner.nextLine();

        Question newQuestion = null;

        switch (type) {
            case 1 -> {
                System.out.print("Enter question text: ");
                String questionText = scanner.nextLine();
                System.out.print("Enter number of options: ");
                int numOptions = scanner.nextInt();
                scanner.nextLine();
                String[] options = new String[numOptions];
                for (int i = 0; i < numOptions; i++) {
                    System.out.print("Enter option " + (i + 1) + ": ");
                    options[i] = scanner.nextLine();
                }
                System.out.print("Enter the correct option number: ");
                int correctAnswerIndex = scanner.nextInt() - 1;
                scanner.nextLine();
                System.out.print("Enter hint: ");
                String hint = scanner.nextLine();
                newQuestion = QuestionFactory.createMultipleChoiceQuestion(questionText, options, correctAnswerIndex, hint, Question.Difficulty.MEDIUM);
            }
            case 2 -> {
                System.out.print("Enter question text: ");
                String questionText = scanner.nextLine();
                System.out.print("Enter correct answer (true/false): ");
                boolean correctAnswer = scanner.nextBoolean();
                scanner.nextLine();
                System.out.print("Enter hint: ");
                String hint = scanner.nextLine();
                newQuestion = QuestionFactory.createTrueFalseQuestion(questionText, correctAnswer, hint, Question.Difficulty.EASY);
            }
            case 3 -> {
                System.out.print("Enter question text: ");
                String questionText = scanner.nextLine();
                System.out.print("Enter the correct answer: ");
                String correctAnswer = scanner.nextLine();
                System.out.print("Enter hint: ");
                String hint = scanner.nextLine();
                newQuestion = QuestionFactory.createFillInTheBlankQuestion(questionText, correctAnswer, hint, Question.Difficulty.HARD);
            }
            default -> System.out.println("Invalid question type selected.");
        }

        if (newQuestion != null) {
            quiz.addQuestion(newQuestion);
            System.out.println("Question added successfully!");
        }
    }

    private static void editQuestionInQuiz(Scanner scanner, Quiz quiz) {
        quiz.displayQuiz();
        System.out.print("Enter question number to edit: ");
        int questionNumber = scanner.nextInt() - 1;
        scanner.nextLine();

        if (questionNumber < 0 || questionNumber >= quiz.getQuestions().size()) {
            System.out.println("Invalid question number.");
            return;
        }

        Question questionToEdit = quiz.getQuestions().get(questionNumber);

        System.out.println("Editing question: " + questionToEdit.getQuestionText());
        System.out.print("Enter new question text: ");
        String questionText = scanner.nextLine();
        questionToEdit.setQuestionText(questionText);

        System.out.println("Question updated successfully.");
    }

    private static void deleteQuestionFromQuiz(Scanner scanner, Quiz quiz) {
        quiz.displayQuiz();
        System.out.print("Enter question number to delete: ");
        int questionNumber = scanner.nextInt() - 1;
        scanner.nextLine();

        if (questionNumber < 0 || questionNumber >= quiz.getQuestions().size()) {
            System.out.println("Invalid question number.");
            return;
        }

        quiz.deleteQuestion(questionNumber);
        System.out.println("Question deleted successfully.");
    }
}
