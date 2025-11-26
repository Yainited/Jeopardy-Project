package yainted;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class GameEvent {
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    protected String caseID;
    protected String playerID;
    protected String activity;
    protected String timestamp;
    protected String category;
    protected String questionValue;
    protected String answerGiven;
    protected String result;
    protected String scoreAfterPlay;

    public GameEvent() {
        caseID = "";
        playerID = "";
        activity = "";
        timestamp = LocalDateTime.now().format(formatter);
        category = "";
        questionValue = "";
        answerGiven = "";
        result = "";
        scoreAfterPlay = "";
    }

    public String getCaseID() {return caseID;}
    public String getPlayerID() {return playerID;}
    public String getActivity() {return activity;}
    public String getTimestamp() {return timestamp;}
    public String getCategory() {return category;}
    public String getQuestionValue() {return questionValue;}
    public String getAnswerGiven() {return answerGiven;}
    public String getResult() {return result;}
    public String getScoreAfterPlay() {return scoreAfterPlay;}
}

