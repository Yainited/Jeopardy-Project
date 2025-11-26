package yainted.events;

public class EnterPlayerNameGameEvent extends GameEvent {
    public EnterPlayerNameGameEvent(String playerID) {
        super();
        this.playerID = playerID;
        this.activity = "Enter Player Name";
        this.answerGiven = playerID;
    }
}