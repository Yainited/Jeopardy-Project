package yainted.events;

public class ExitGameEvent extends GameEvent {
    public ExitGameEvent(String caseID) {
        super();
        this.caseID = caseID;
        this.playerID = "System";
        this.activity = "Exit Game";
    }
}