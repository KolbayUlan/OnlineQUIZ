package service.Observer;

public class ConsoleScoreObserver implements ScoreObserver {
    @Override
    public void update(int score) {
        System.out.println("Current Score: " + score);
    }
}