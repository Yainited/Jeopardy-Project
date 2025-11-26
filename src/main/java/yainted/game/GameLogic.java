package yainted.game;

import java.util.List;

import yainted.model.Question;

public class GameLogic {
    
    private PlayerManager playerManager;
    private QuestionManager questionManager;

    public GameLogic(List<Player> players, List<Question> questions) {
        this.questionManager = new QuestionManager(questions);
        this.playerManager = new PlayerManager(players);
    }

    public void PlayerChooseQuestion(String questionID) {
        questionManager.setCurrentQuestion(questionID);
    }

    public void NextTurn() {
        playerManager.advanceTurn();
    }

    public void AnswerQuestion(String answer) {
        Question currentQuestion = questionManager.getCurrentQuestion();
        if (currentQuestion == null) {
            throw new IllegalStateException("No current question to answer.");
        }
        Player currentPlayer = playerManager.getCurrentPlayer();
        if (currentQuestion.getAnswer().equalsIgnoreCase(answer)) {
            currentPlayer.addScore(currentQuestion.getValue());
        } 
        else {
            currentPlayer.deductScore(currentQuestion.getValue());
        }
        questionManager.setCurrentQuestion(null);
        NextTurn();
    }

}
