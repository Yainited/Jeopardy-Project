package yainted.events;
/**
 * Represents a game event for when the game is exited.
 * Records the exit activity with the player ID set to "System".
 */
public class ExitGameEvent extends GameEvent {

    public ExitGameEvent() {
        super();
        this.playerID = "System";
        this.activity = "Exit Game";
    }
}