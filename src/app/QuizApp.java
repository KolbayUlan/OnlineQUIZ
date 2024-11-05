package app;

import controller.facade.QuizFacade;
import controller.state.*;

import java.util.Scanner;

public class QuizApp {
    private State currentState;
    private Long userID;
    private final Scanner scanner;
    private final QuizFacade quizFacade;

    public QuizApp() {
        this.scanner = new Scanner(System.in);
        this.quizFacade = new QuizFacade(); // Initialize QuizFacade
        this.currentState = new LoginState(this, scanner, quizFacade);
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

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public QuizFacade getQuizFacade() {
        return quizFacade;
    }
}
