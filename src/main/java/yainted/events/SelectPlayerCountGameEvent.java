package yainted.events;

public class SelectPlayerCountGameEvent extends GameEvent {
    public SelectPlayerCountGameEvent(String caseID, int playerCount) {
        super();
        this.caseID = caseID;
        this.playerID = "System";
        this.activity = "Select Player Count";
        this.answerGiven = Integer.toString(playerCount);
    }
}