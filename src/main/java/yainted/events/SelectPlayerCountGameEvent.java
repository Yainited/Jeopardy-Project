package yainted.events;
/**
 * Represents a game event for when the player count is selected.
 * Records the number of players chosen with the player ID set to "System".
 */
public class SelectPlayerCountGameEvent extends GameEvent {
    /**
     * Creates a new select player count game event with the specified count.
     *
     * @param playerCount the number of players selected for the game
     */
    public SelectPlayerCountGameEvent(int playerCount) {
        super();
        this.playerID = "System";
        this.activity = "Select Player Count";
        this.answerGiven = Integer.toString(playerCount);
    }
}