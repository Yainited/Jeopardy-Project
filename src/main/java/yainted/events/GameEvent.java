package yainted.events;
import yainted.observer.EventManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Abstract base class representing a game event in the system.
 * Contains common attributes for tracking player activities, questions,
 * answers, and scoring information during gameplay.
 */
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

    /**
     * Creates a new game event with default values.
     * Initializes the case ID from the EventManager and sets the timestamp to current time.
     * All other fields are initialized as empty strings.
     */
    public GameEvent() {
        caseID = EventManager.getInstance().getCaseid();
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

