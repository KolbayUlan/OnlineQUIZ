package controller.state;

import app.QuizApp;
import controller.UserController;
import model.Quiz;
import controller.QuizController;
import service.Observer.ConsoleScoreObserver;

public class QuizState implements State {
    private final QuizApp quizApp;
    private final Quiz quiz;
    private final QuizController quizController;

    public QuizState(QuizApp quizApp, Quiz quiz) {
        this.quizApp = quizApp;
        this.quiz = quiz;
        this.quizController = new QuizController();
    }

    @Override
    public void handle() {
        ConsoleScoreObserver scoreObserver = new ConsoleScoreObserver();
        quiz.addObserver(scoreObserver);
        quizController.start(quiz);
        quizApp.setState(new LoginState(quizApp, quizApp.getScanner(), new UserController()));
    }
}
