package model;

public class MultipleChoiceQuestion extends Question {
    private String[] options;
    private int correctAnswerIndex;

    public MultipleChoiceQuestion(String questionText, String[] options, int correctAnswerIndex, String hint, Difficulty difficulty) {
        super(questionText, hint, difficulty);
        this.options= options;
        this.correctAnswerIndex = correctAnswerIndex;
    }


    @Override
    public void display() {
        System.out.println(questionText);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }


    @Override
    public boolean checkAnswer(String userAnswer) {
        try {
            int answerIndex =Integer.parseInt(userAnswer) -1;
            return answerIndex == correctAnswerIndex;
        } catch (Exception e) {
            return false;
        }
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
