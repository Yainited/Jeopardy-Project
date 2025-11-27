package yainted.events;
/**
 * Represents a game event for when a file is loaded.
 * Records the file loading activity with the player ID set to "System"
 * and the result of the load operation.
 */
public class LoadFileGameEvent extends GameEvent {
    /**
     * Creates a new load file game event with the specified result.
     *
     * @param result the result of the file loading operation
     */
    public LoadFileGameEvent(String result) {
        super();
        this.playerID = "System";
        this.activity = "Load File";
        this.result = result;
    }
}