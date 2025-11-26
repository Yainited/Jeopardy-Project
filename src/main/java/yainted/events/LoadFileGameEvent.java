package yainted.events;

public class LoadFileGameEvent extends GameEvent {
    public LoadFileGameEvent(String caseID, String result) {
        super();
        this.caseID = caseID;
        this.playerID = "System";
        this.activity = "Load File";
        this.result = result;
    }
}