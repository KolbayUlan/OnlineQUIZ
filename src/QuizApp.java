// File: src/QuizApp.java

public class QuizApp {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();

        // Adding multiple choice question
        quiz.addQuestion(QuestionFactory.createMultipleChoiceQuestion(
                "What is the capital of France?",
                new String[]{"Berlin", "London", "Paris", "Madrid"},
                2,
                "It's also called the city of love.",
                Question.Difficulty.EASY
        ));

        // Adding true/false question
        quiz.addQuestion(QuestionFactory.createTrueFalseQuestion(
                "The Earth is flat.",
                false,
                "Think about gravity!",
                Question.Difficulty.EASY
        ));

        // Adding fill-in-the-blank question
        quiz.addQuestion(QuestionFactory.createFillInTheBlankQuestion(
                "What is the largest planet in our Solar System?",
                "Jupiter",
                "It is a gas giant.",
                Question.Difficulty.MEDIUM
        ));

        // Start the quiz
        quiz.start();
    }
}
