// File: src/Quiz.java

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz {
    private List<Question> questions;
    private int totalScore;

    public Quiz() {
        this.questions = new ArrayList<>();
        this.totalScore = 0;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
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

            System.out.println(score > 0 ? "Correct!" : "Incorrect.");
            System.out.println("Score for this question: " + score + "\n");
        }
        scanner.close();
        System.out.println("Quiz finished! Total score: " + totalScore + "/" + (questions.size() * 5));
    }
}