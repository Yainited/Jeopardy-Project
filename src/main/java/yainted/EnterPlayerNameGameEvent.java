package yainted;

public class EnterPlayerNameGameEvent extends GameEvent {
    public EnterPlayerNameGameEvent(String caseID, String playerID, String playerName) {
        super();
        this.caseID = caseID;
        this.playerID = playerID;
        this.activity = "Enter Player Name";
        this.answerGiven = playerName;
    }
}