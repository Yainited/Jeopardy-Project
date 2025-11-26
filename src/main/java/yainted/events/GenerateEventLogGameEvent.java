package yainted.events;

public class GenerateEventLogGameEvent extends GameEvent {
    public GenerateEventLogGameEvent(String caseID) {
        super();
        this.caseID = caseID;
        this.playerID = "System";
        this.activity = "Generate Event Log";
    }
}