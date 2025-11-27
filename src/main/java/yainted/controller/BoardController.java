package yainted.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;

import yainted.events.SelectQuestionGameEvent;
import yainted.manager.GameData;
import yainted.manager.PlayerManager;
import yainted.manager.QuestionManager;
import yainted.model.Player;
import yainted.model.Question;
import yainted.observer.EventManager;

/**
 * Controller for handling the GameScreen Panel.
 */
public class BoardController {
    private GameData gameData;

     public BoardController(GameData gameData) {
        this.gameData = gameData;
    }

    /** Retrieves the list of players.
     * @return The list of players.
     */
    public ArrayList<Player> getPlayers()
    {
        PlayerManager pm = gameData.getPlayerManager();
        return pm.getPlayers();
    }

    /** Selects a question based on its id which is formed by concatenating the category and value.
     * @param value The value of the question.
     * @param category The category of the question.
     */
    public void selectQuestion(String value, String category)
    {
        String id = category + "_" + value;
        QuestionManager qm = gameData.getQuestionManager();
        Question question = qm.getQuestionByID(id, category);
        gameData.setCurrentQuestion(question);
        EventManager.getInstance().notifyObservers(new SelectQuestionGameEvent(gameData.getCurrentPlayer().getName(), gameData.getCurrentQuestion()));
    }

    /** Retrieves the list of category names.
     * @return The list of category names.
     */
    public ArrayList<String> getCategoryNames()
    {
        QuestionManager qm = gameData.getQuestionManager();
        return new ArrayList<>(qm.getCategories().keySet());
    }

    /** Checks if the game is over.
     * @return True if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        ArrayList<Question> allQuestions = gameData.getQuestionManager().getAllQuestions();
        return gameData.getQuestionManager().getAnsweredQuestions() >= allQuestions.size();
    }

    /** Retrieves the current player.
     * @return The current Player object.
     */
   public Player getCurrentPlayer() {
        return gameData.getCurrentPlayer();
    }
}
