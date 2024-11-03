package service.Decorator;

import model.Question;

public class HintDecorator extends QuestionDecorator {
    public HintDecorator(Question question) {
        super(question);
    }

    @Override
    public void display() {
        question.display();
        System.out.println("Hint: " + question.getHint());
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return question.checkAnswer(userAnswer);
    }

    @Override
    public int calculateScore(String userAnswer) {
        return question.calculateScore(userAnswer);
    }
}
