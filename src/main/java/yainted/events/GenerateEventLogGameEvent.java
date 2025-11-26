package yainted.events;

public class GenerateEventLogGameEvent extends GameEvent {
    public GenerateEventLogGameEvent() {
        super();
        this.playerID = "System";
        this.activity = "Generate Event Log";
    }
}