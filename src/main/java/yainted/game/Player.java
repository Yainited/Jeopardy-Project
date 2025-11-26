package yainted.game;

public class Player {

    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public void addScore(int points) {
        score += points;
    }

    public void deductScore(int points) {
        score -= points;
    }

    public String getName() { return name; }
    public int getScore() { return score; }
}
