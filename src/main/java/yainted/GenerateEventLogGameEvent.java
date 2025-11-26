package yainted;

public class GenerateEventLogGameEvent extends GameEvent {
    public GenerateEventLogGameEvent(String caseID, String result) {
        super();
        this.caseID = caseID;
        this.playerID = "System";
        this.activity = "Generate Event Log";
        this.result = result;
    }
}