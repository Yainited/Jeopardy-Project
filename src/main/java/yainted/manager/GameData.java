package yainted.manager;

import java.util.List;
import java.util.ArrayList;

import yainted.model.Player;
import yainted.model.Question;
import yainted.model.TurnSnapshot;

/** Class representing the game data, including players, questions, and the current state of the game. */
public class GameData {
    private PlayerManager playerManager = new PlayerManager();
    private QuestionManager questionManager = new QuestionManager();
    private Player currentPlayer;
    private Question currentQuestion;
    private List<TurnSnapshot> turnHistory;

    public GameData(PlayerManager pm, QuestionManager qm) {
        this.playerManager = pm;
        this.questionManager = qm;
        this.currentPlayer = null;
        this.currentQuestion = null;
        this.turnHistory = new ArrayList<>();
    }
    
    /** Retrieves the current player.
     * @return The current Player object.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    /** Retrieves the current question.
     * @return The current Question object.
     */
    public Question getCurrentQuestion() {
        return currentQuestion;
    }
    
    /** Sets the current player.
     * @param player The Player object to set as the current player.
     */
    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }
    /** Sets the current question.
     * @param question The Question object to set as the current question.
     */
    public void setCurrentQuestion(Question question) {
        this.currentQuestion = question;
    }
    
    /** Retrieves the player manager.
     * @return The PlayerManager object.
     */
    public PlayerManager getPlayerManager() {
        return playerManager;
    }
    /** Retrieves the question manager.
     * @return The QuestionManager object.
     */
    public QuestionManager getQuestionManager() {
        return questionManager;
    }
    /** Advances to the next player's turn and resets the current question. */
    public void nextTurn()
    {
        currentPlayer = playerManager.getNextPlayer(currentPlayer);
        
        currentQuestion = null;
    }
    public void addTurnSnapshot(TurnSnapshot snapshot) {
        turnHistory.add(snapshot);
    }
    /** Retrieves the turn history.
     * @return A list of TurnSnapshot objects representing the turn history.
     */
    public List<TurnSnapshot> getTurnHistory() { return turnHistory; }
}
