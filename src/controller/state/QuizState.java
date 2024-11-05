package controller.state;

import app.QuizApp;
import controller.facade.QuizFacade;

public class QuizState implements State {
    private final QuizApp quizApp;
    private final QuizFacade quizFacade;

    public QuizState(QuizApp quizApp, QuizFacade quizFacade) {
        this.quizApp = quizApp;
        this.quizFacade = quizFacade;
    }

    @Override
    public void handle() {
        System.out.println("Starting the quiz...");

        // Start the quiz using the facade
        quizFacade.startQuiz();

        // After finishing the quiz, return to the appropriate state
        if (quizApp.getUserID() == null) {
            // Go to login state if the user is not logged in
            quizApp.setState(new LoginState(quizApp, quizApp.getScanner(), quizFacade));
        } else if (quizApp.getUserID() == 0L) {
            // Return to AdminState if the user is an admin
            quizApp.setState(new AdminState(quizApp, quizApp.getScanner(), quizFacade));
        } else {
            // Return to UserState if the user is a regular user
            quizApp.setState(new UserState(quizApp, quizApp.getScanner(), quizFacade));
        }
    }
}
