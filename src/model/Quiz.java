package model;

import service.Observer.ScoreObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz {
    private List<Question> questions;
    private int totalScore;
    private List<ScoreObserver> observers;

    public Quiz() {
        this.questions = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.totalScore = 0;
    }

    public void addObserver(ScoreObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (ScoreObserver observer : observers) {
            observer.update(totalScore);
        }
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void deleteQuestion(int questionIndex) {
        if (questionIndex >= 0 && questionIndex < questions.size()) {
            questions.remove(questionIndex);
        }
    }



    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
