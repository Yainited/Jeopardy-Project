package yainted.controller;

import yainted.events.AnswerQuestionGameEvent;
import yainted.events.SelectQuestionGameEvent;
import yainted.manager.GameData;
import yainted.model.Player;
import yainted.model.Question;
import yainted.model.TurnSnapshot;
import yainted.observer.EventManager;

/**
 * Controller for handling the SelectedQuestion Panel.
 */
public class QuestionController {
   
    private GameData gameData;

    public QuestionController(GameData gameData) {
        this.gameData = gameData;
    }

    /** Retrieves the current question.
     * @return The current Question object.
     */
    public Question getCurrentQuestion()
    {
        return this.gameData.getCurrentQuestion();
    }

    /** Validates the given answer.
     * @param ans The answer to validate.
     * @return True if the answer is correct, false otherwise.
     */
    public Boolean validateAnswer(String ans)
    {
        Question currentQuestion = gameData.getCurrentQuestion();
        Boolean isCorrect = currentQuestion.getAnswer().equals(ans);
        gameData.getQuestionManager().incrementAnsweredQuestions();
        Player currentPlayer = gameData.getCurrentPlayer();
        if (isCorrect)
        {
            currentPlayer.addScore(currentQuestion.getValue());
            EventManager.getInstance().notifyObservers(new AnswerQuestionGameEvent(currentPlayer, currentQuestion, currentQuestion.getOptionValue(ans),"Correct"));
            gameData.addTurnSnapshot(new TurnSnapshot(gameData.getTurnHistory().size() + 1, currentPlayer, currentQuestion, currentQuestion.getOptionValue(ans), true));
        }
        else
        {
            int penalty = currentQuestion.getValue();
            currentPlayer.addScore(-penalty);
            EventManager.getInstance().notifyObservers(new AnswerQuestionGameEvent(currentPlayer, currentQuestion, currentQuestion.getOptionValue(ans),"Incorrect"));
            gameData.addTurnSnapshot(new TurnSnapshot(gameData.getTurnHistory().size() + 1, currentPlayer, currentQuestion, currentQuestion.getOptionValue(ans), false));
        }
        gameData.nextTurn();
        return isCorrect;
    }
}
