package yainted.model;

public class TurnSnapshot {
    String playerName;
    int currentScore;
    String category;
    String question;
    int questionValue;
    Boolean correct;

    public TurnSnapshot(String playerName, int currentScore, String category, String question, int questionValue, Boolean correct) {
        this.playerName = playerName;
        this.currentScore = currentScore;
        this.category = category;
        this.question = question;
        this.questionValue = questionValue;
        this.correct = correct;
    }

    public String getPlayerName() {return  playerName;}
    public int getCurrentScore() {return currentScore;}
    public String getCategory() {return category;}
    public String getQuestion() {return question;}
    public int getQuestionValue() {return questionValue;}
    public Boolean getCorrect() {return correct;}
}