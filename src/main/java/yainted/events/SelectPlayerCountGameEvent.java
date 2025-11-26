package yainted.events;

public class SelectPlayerCountGameEvent extends GameEvent {
    public SelectPlayerCountGameEvent(int playerCount) {
        super();
        this.playerID = "System";
        this.activity = "Select Player Count";
        this.answerGiven = Integer.toString(playerCount);
    }
}