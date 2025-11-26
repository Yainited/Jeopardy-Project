package yainted.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import yainted.events.AnswerQuestionGameEvent;
import yainted.events.EnterPlayerNameGameEvent;
import yainted.events.SelectPlayerCountGameEvent;
import yainted.events.SelectQuestionGameEvent;
import yainted.gui.SelectedQuestion;
import yainted.model.Question;
import yainted.model.TurnSnapshot;
import yainted.observer.EventManager;

public class GameLogic {

    private List<TurnSnapshot> turnHistory;
    private PlayerManager playerManager;
    private QuestionManager questionManager;

    public GameLogic(ArrayList<Question> questions) {
        turnHistory = new ArrayList<>();
        this.questionManager = new QuestionManager(questions);
    }

    public void PlayerChooseQuestion(String questionID) {
        questionManager.setCurrentQuestion(questionID);
        EventManager.getInstance().notifyObservers(new SelectQuestionGameEvent(playerManager.getCurrentPlayer().getName(), questionManager.getCurrentQuestion()));
    }
    public void NextTurn() {
        playerManager.advanceTurn();
    }

    public void setPlayers(ArrayList<String> players) {
        EventManager.getInstance().notifyObservers(new SelectPlayerCountGameEvent(players.size()));
        this.playerManager = new PlayerManager(players);
        for  (String name : players) {
            EventManager.getInstance().notifyObservers(new EnterPlayerNameGameEvent(name));
        }
    }

    public Question getQuestionByID(String questionID) {
        return questionManager.getQuestionById(questionID);
    }
    public ArrayList<Player> getPlayers() {
        return playerManager.getPlayers();
    }
    public Question getCurrentQuestion() {
        return questionManager.getCurrentQuestion();
    }

    public Boolean AnswerQuestion(String answer) {
        Question currentQuestion = questionManager.getCurrentQuestion();
        if (currentQuestion == null) {
            throw new IllegalStateException("No current question to answer.");
        }
        Player currentPlayer = playerManager.getCurrentPlayer();
        if (currentQuestion.getAnswer().equalsIgnoreCase(answer)) {

            currentPlayer.addScore(currentQuestion.getValue());
            EventManager.getInstance().notifyObservers(new AnswerQuestionGameEvent(currentPlayer, currentQuestion, currentQuestion.getOptionValue(answer),"Correct"));
            turnHistory.add(new TurnSnapshot(turnHistory.size() + 1, currentPlayer, currentQuestion, currentQuestion.getOptionValue(answer), true));
            questionManager.setCurrentQuestion(null);
            NextTurn();
            return true;
        } 
        else {
            currentPlayer.deductScore(currentQuestion.getValue());
            EventManager.getInstance().notifyObservers(new AnswerQuestionGameEvent(currentPlayer, currentQuestion, currentQuestion.getOptionValue(answer),"Incorrect"));
            turnHistory.add(new TurnSnapshot(turnHistory.size() + 1, currentPlayer, currentQuestion, currentQuestion.getOptionValue(answer), false));
            questionManager.setCurrentQuestion(null);
            NextTurn();
            return false;
        }
    }
    public List<TurnSnapshot> getTurnHistory() { return turnHistory; }
}
