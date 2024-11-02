// File: src/TrueFalseQuestion.java

public class TrueFalseQuestion extends Question {
    private boolean correctAnswer;

    public TrueFalseQuestion(String questionText, boolean correctAnswer, String hint, Difficulty difficulty) {
        super(questionText, hint, difficulty);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public void display() {
        System.out.println(questionText);
        System.out.println("1. True\n2. False");
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return (userAnswer.equalsIgnoreCase("1") && correctAnswer) ||
                (userAnswer.equalsIgnoreCase("2") && !correctAnswer);
    }

    @Override
    public int calculateScore(String userAnswer) {
        return checkAnswer(userAnswer) ? getScoreBasedOnDifficulty() : 0;
    }

    private int getScoreBasedOnDifficulty() {
        return switch (difficulty) {
            case EASY -> 1;
            case MEDIUM -> 3;
            case HARD -> 5;
        };
    }
}
