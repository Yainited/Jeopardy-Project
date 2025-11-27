package yainted.model;

/**
 * Represents a snapshot of a turn in the game.
 */
public class TurnSnapshot {
    private int turnNumber;
    private String playerName;
    private String currentScore;
    private String category;
    private String question;
    private String questionValue;
    private String answerGiven;
    private Boolean correct;

    /**
     * Creates a new turn snapshot with the specified turn details.
     *
     * @param turnNumber the sequential number of this turn
     * @param player the player taking the turn
     * @param question the question being answered
     * @param answerGiven the answer provided by the player
     * @param correct whether the answer was correct
     */
    public TurnSnapshot(int turnNumber, Player player, Question question, String answerGiven, Boolean correct) {
        this.turnNumber = turnNumber;
        this.playerName = player.getName();
        this.currentScore = String.valueOf(player.getScore());
        this.category = question.getCategory();
        this.question = question.getText();
        this.questionValue = String.valueOf(question.getValue());
        this.answerGiven = answerGiven;
        this.correct = correct;
    }

    public int getTurnNumber() { return turnNumber; }
    public String getPlayerName() {return  playerName;}
    public String getCurrentScore() {return currentScore;}
    public String getCategory() {return category;}
    public String getQuestion() {return question;}
    public String getQuestionValue() {return questionValue;}
    public Boolean isCorrect() {return correct;}
    public String getAnswerGiven() {return answerGiven;}


    public String toString(){
        return String.format("%s %s %s %s %s %b", playerName, currentScore, category, question, questionValue, correct);
    }
}