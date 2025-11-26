package yainted.game;

import java.util.ArrayList;

import yainted.model.Question;

public class GameLogic {
    
    private PlayerManager playerManager;
    private QuestionManager questionManager;

    public GameLogic(ArrayList<Question> questions) {
        this.questionManager = new QuestionManager(questions);
    }

    public void PlayerChooseQuestion(String questionID) {
        questionManager.setCurrentQuestion(questionID);
    }

    public void NextTurn() {
        playerManager.advanceTurn();
    }

    public void setPlayers(ArrayList<String> players) {
        this.playerManager = new PlayerManager(players);
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
            questionManager.setCurrentQuestion(null);
            NextTurn();
            return true;
        } 
        else {
            currentPlayer.deductScore(currentQuestion.getValue());
            questionManager.setCurrentQuestion(null);
            NextTurn();
            return false;
        }
    }

}
