package yainted.events;
/**
 * Represents a game event for when the game starts.
 * Records the game start activity with the player ID set to "System".
 */
public class StartGameEvent extends GameEvent {

    public StartGameEvent() {
        super();
        this.playerID = "System";
        this.activity = "Start Game";
    }
}