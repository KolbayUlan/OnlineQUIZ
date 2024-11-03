
public abstract class Question {
    protected String questionText;
    protected String hint;
    protected Difficulty difficulty;

    public enum Difficulty {
        EASY, MEDIUM, HARD
    }


    public Question(String questionText, String hint, Difficulty difficulty) {
        this.questionText = questionText;
        this.hint = hint;
        this.difficulty = difficulty;
    }


    public String getQuestionText() {
        return questionText;
    }


    public String getHint() {
        return hint;
    }


    public Difficulty getDifficulty() {
        return difficulty;
    }

    public abstract void display();
    public abstract boolean checkAnswer(String userAnswer);
    public abstract int calculateScore(String userAnswer);
}
