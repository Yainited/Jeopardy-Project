package yainted.events;

public class AnswerQuestionGameEvent extends GameEvent {
    public AnswerQuestionGameEvent(String caseID, String playerID, String category, int questionValue, String answerGiven, String result, int currentScore) {
        super();
        this.caseID = caseID;
        this.playerID = playerID;
        this.activity = "Answer Question";
        this.category = category;
        this.questionValue = Integer.toString(questionValue);
        this.answerGiven = answerGiven;
        this.result = result;
        this.scoreAfterPlay = Integer.toString(currentScore);
    }
}