package service.Decorator;

import model.Question;

public abstract class QuestionDecorator extends Question {
    protected Question question;

    public QuestionDecorator(Question question) {
        super(question.getQuestionText(), question.getHint(), question.getDifficulty());
        this.question = question;
    }
}
