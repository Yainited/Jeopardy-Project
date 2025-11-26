package yainted;

public class StartGameEvent extends GameEvent {
    public StartGameEvent(String caseID) {
        super();
        this.caseID = caseID;
        this.playerID = "System";
        this.activity = "Start Game";
    }
}