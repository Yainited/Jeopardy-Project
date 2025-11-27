package yainted.events;

import yainted.model.Question;
/**
 * Represents a game event for when a player selects a question.
 * Records the player ID, question category, and question value.
 */
public class SelectQuestionGameEvent extends GameEvent {
    /**
     * Creates a new select question game event with the specified details.
     *
     * @param playerID the unique identifier of the player selecting the question
     * @param question the question that was selected
     */
    public SelectQuestionGameEvent(String playerID, Question question) {
        super();
        this.playerID = playerID;
        this.activity = "Select Question";
        this.category = question.getCategory();
        this.questionValue = String.valueOf(question.getValue());
    }
}