// File: src/QuizApp.java

import model.Question;
import model.QuestionFactory;
import service.Observer.ConsoleScoreObserver;
import service.Quiz;

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

        ConsoleScoreObserver scoreObserver = new ConsoleScoreObserver();
        quiz.addObserver(scoreObserver);

        quiz.start();
    }
}
