package yainted.events;
/**
 * Represents a game event for when a player enters their name.
 * Records the player identification and marks the activity as name entry.
 */
public class EnterPlayerNameGameEvent extends GameEvent {
    /**
     * Creates a new enter player name game event.
     *
     * @param playerID the unique identifier for the player
     */
    public EnterPlayerNameGameEvent(String playerID) {
        super();
        this.playerID = playerID;
        this.activity = "Enter Player Name";
        this.answerGiven = playerID;
    }
}