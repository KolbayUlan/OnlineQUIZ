package controller;

import model.Question;
import model.QuestionFactory;
import model.Quiz;

import java.util.List;
import java.util.Scanner;

public class QuizController {
    public void addQuestionToQuiz(Scanner scanner, Quiz quiz) {
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

    public void editQuestionInQuiz(Scanner scanner, Quiz quiz) {
        displayQuiz(quiz);
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

    public void deleteQuestionFromQuiz(Scanner scanner, Quiz quiz) {
        displayQuiz(quiz);
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

    public void start(Quiz quiz) {
        Scanner scanner = new Scanner(System.in);
        int maxScore = 0, totalScore = 0;
        List<Question> questions = quiz.getQuestions();

        for (Question question : questions) {
            question.display();

            System.out.print("Need a hint? (yes/no): ");
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                System.out.println("Hint: " + question.getHint());
            }

            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine();

            int score = question.calculateScore(userAnswer);
            totalScore += score;

            maxScore += switch (question.getDifficulty()) {
                case EASY -> 1;
                case MEDIUM -> 3;
                case HARD -> 5;
            };

            System.out.println(score > 0 ? "Correct!" : "Incorrect.");
            System.out.println("Score for this question: " + score + "\n");

            // Notify observers after each question
            quiz.notifyObservers();
        }

        System.out.println("Quiz finished! Total score: " + totalScore + "/" + maxScore);
    }

    public void displayQuiz(Quiz quiz) {
        System.out.println("Quiz Questions:");
        for (Question question : quiz.getQuestions()) {
            question.display();
        }
    }

}
