public class FillInTheBlankQuestion extends Question {
    private String correctAnswer;

    public FillInTheBlankQuestion(String questionText, String correctAnswer, String hint, Difficulty difficulty) {
        super(questionText, hint, difficulty);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public void display() {
        System.out.println(questionText + " (Please type your answer:)");
    }


    @Override
    public boolean checkAnswer(String userAnswer) {
        return userAnswer.equalsIgnoreCase(correctAnswer);
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
