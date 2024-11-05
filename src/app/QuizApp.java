package app;

import model.Quiz;
import controller.state.LoginState;
import controller.state.State;
import controller.UserController;

import java.util.Scanner;

public class QuizApp {
    private State currentState;
    private Long userID;
    private final Scanner scanner;
    private Quiz quiz;

    public QuizApp() {
        this.scanner = new Scanner(System.in);
        this.currentState = new LoginState(this, scanner, new UserController());
        this.quiz = new Quiz();
    }

    public static void main(String[] args) {
        QuizApp app = new QuizApp();
        while (true) {
            app.currentState.handle();
        }
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getUserID() {
        return userID;
    }

    public Scanner getScanner() {
        return scanner;
    }
    public Quiz getQuiz() {
        return quiz;
    }
}
