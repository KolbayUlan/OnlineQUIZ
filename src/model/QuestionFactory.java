package model;

public class QuestionFactory {
    public static Question createMultipleChoiceQuestion(String questionText, String[] options, int correctAnswerIndex, String hint, Question.Difficulty difficulty) {
        return new MultipleChoiceQuestion(questionText, options, correctAnswerIndex, hint, difficulty);
    }

    public static Question createTrueFalseQuestion(String questionText, boolean correctAnswer, String hint, Question.Difficulty difficulty) {
        return new TrueFalseQuestion(questionText, correctAnswer, hint, difficulty);
    }

    public static Question createFillInTheBlankQuestion(String questionText, String correctAnswer, String hint, Question.Difficulty difficulty) {
        return new FillInTheBlankQuestion(questionText, correctAnswer, hint, difficulty);
    }
}
