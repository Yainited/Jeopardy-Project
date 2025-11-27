package yainted.events;

import yainted.model.Player;
import yainted.model.Question;
/**
 * Represents a game event specifically for answering questions.
 * Extends GameEvent to track question-answering activities including
 * the question category, value, player's answer, result, and updated score.
 */
public class AnswerQuestionGameEvent extends GameEvent {

    /**
     * Creates a new answer question game event with the specified details.
     *
     * @param player the player answering the question
     * @param question the question being answered
     * @param answerGiven the answer provided by the player
     * @param result the result of the answer attempt (correct/incorrect)
     */
    public AnswerQuestionGameEvent(Player player, Question question, String answerGiven, String result) {
        super();
        this.playerID = player.getName();
        this.activity = "Answer Question";
        this.category = question.getCategory();
        this.questionValue = Integer.toString(question.getValue());
        this.answerGiven = answerGiven;
        this.result = result;
        this.scoreAfterPlay = Integer.toString(player.getScore());
    }
}