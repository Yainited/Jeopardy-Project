package yainted.model;

/**
 * Represents a player in the game.
 */
public class Player {

    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    /** Adds points to the player's score (also uses negative numbers).
     * @param points The number of points to add.
     */
    public void addScore(int points) {
        score += points;
    }

    /** Retrieves the player's name.
     * @return The player's name.
     */
    public String getName() { return name; }
    
    /** Retrieves the player's score.
     * @return The player's score.
     */
    public int getScore() { return score; }
}
