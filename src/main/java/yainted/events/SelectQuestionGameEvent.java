package yainted.events;

public class SelectQuestionGameEvent extends GameEvent {
    public SelectQuestionGameEvent(String caseID, String playerID, String category, int questionValue) {
        super();
        this.caseID = caseID;
        this.playerID = playerID;
        this.activity = "Select Question";
        this.category = category;
        this.questionValue = Integer.toString(questionValue);
    }
}