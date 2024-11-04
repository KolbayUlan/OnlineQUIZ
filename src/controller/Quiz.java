package controller;

import model.Question;
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

    private void notifyObservers() {
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
            System.out.println("Question deleted successfully.");
        } else {
            System.out.println("Invalid question index. Deletion failed.");
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int maxScore = 0;

        for (Question question : questions) {
            question.display();

            System.out.print("Need a hint? (yes/no): ");
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                System.out.println("Hint: " + question.getHint());
            }

            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine();

            int score = question.calculateScore(userAnswer);
            totalScore += score;

            maxScore += switch (question.getDifficulty()) {
                case EASY -> 1;
                case MEDIUM -> 3;
                case HARD -> 5;
            };

            System.out.println(score > 0 ? "Correct!" : "Incorrect.");
            System.out.println("Score for this question: " + score + "\n");

            // Notify observers after each question
            notifyObservers();
        }

        System.out.println("Quiz finished! Total score: " + totalScore + "/" + maxScore);
    }

    public void displayQuiz() {
        System.out.println("Quiz Questions:");
        for (Question question : questions) {
            question.display();
        }
    }
}
