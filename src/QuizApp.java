// File: src/QuizApp.java

import controller.UserController;
import model.Question;
import model.QuestionFactory;
import repository.DatabaseConnection;
import service.Observer.ConsoleScoreObserver;
import service.Quiz;

import java.sql.SQLException;
import java.util.Scanner;

public class QuizApp {
    public static void main(String[] args) {

        Quiz quiz = new Quiz();


        quiz.addQuestion(QuestionFactory.createMultipleChoiceQuestion(
                "What is the capital of France?",
                new String[]{"Berlin", "London", "Paris", "Madrid"},
                2,
                "It's also called the city of love.",
                Question.Difficulty.EASY
        ));

        quiz.addQuestion(QuestionFactory.createTrueFalseQuestion(
                "The Earth is flat.",
                false,
                "Think about gravity!",
                Question.Difficulty.EASY
        ));

        quiz.addQuestion(QuestionFactory.createFillInTheBlankQuestion(
                "What is the largest planet in our Solar System?",
                "Jupiter",
                "It is a gas giant.",
                Question.Difficulty.MEDIUM
        ));
        UserController userController = new UserController();
        Long UserID = null;
        while(true) {
            System.out.println("Please enter your choice: \n1. Sign in\n2. Sign up\n3. Exit");
            Scanner sc = new Scanner(System.in);
            int cur = sc.nextInt();
            if (cur == 1) {
                UserID = userController.loginUser();
                break;
            } else if (cur == 2) {
                userController.registerUser();

            } else if (cur == 3) {
                System.exit(0);
            }
        }
        System.out.println("Current userID: "+ UserID);
        ConsoleScoreObserver scoreObserver = new ConsoleScoreObserver();
        quiz.addObserver(scoreObserver);

        quiz.start();
    }
}
