package yainted.events;

import yainted.model.Question;

public class SelectQuestionGameEvent extends GameEvent {
    public SelectQuestionGameEvent(String playerID, Question question) {
        super();
        this.playerID = playerID;
        this.activity = "Select Question";
        this.category = question.getCategory();
        this.questionValue = String.valueOf(question.getValue());
    }
}