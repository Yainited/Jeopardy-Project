package yainted.events;
/**
 * Represents a game event for when an event log is generated.
 * Records the log generation activity with the player ID set to "System".
 */
public class GenerateEventLogGameEvent extends GameEvent {
    public GenerateEventLogGameEvent() {
        super();
        this.playerID = "System";
        this.activity = "Generate Event Log";
    }
}