package yainted.events;

import yainted.game.Player;
import yainted.model.Question;

public class AnswerQuestionGameEvent extends GameEvent {
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