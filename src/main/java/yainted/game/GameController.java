package yainted.game;
import yainted.gui.GameView;
import java.util.ArrayList;
import yainted.model.Question;

public class GameController {
    private GameLogic gameLogic;
    private GameView gameView;

    public GameController() {
        this.gameView = new GameView(this);
    }

    public void getFilePath(String path)
    {
        ArrayList<Question> questions = dataInitializer.getQuestions(path);
        if (questions.isEmpty()) {
            throw new IllegalArgumentException("No questions loaded from the file: " + path);
        }
        this.gameLogic = new GameLogic(questions);
    }
    public void getPlayers(java.util.ArrayList<String> players)
    {
        this.gameLogic.setPlayers(players);
    }
    public ArrayList<Player> sendPlayersToView()
    {
        return this.gameLogic.getPlayers();
    }
    public Question selectQuestion(String value, String category)
    {
        String id = category + "_" + value;
        this.gameLogic.PlayerChooseQuestion(id);
        return this.gameLogic.getQuestionByID(id);
    }
    public Question getCurrentQuestion()
    {
        return this.gameLogic.getCurrentQuestion();
    }
    public void setReportFormat(String format)
    {
        // Pass the report format to the GameLogic or relevant component
    }
    public Boolean validateAnswer(String ans)
    {
        Boolean test =  this.gameLogic.AnswerQuestion(ans);
        return test;
    }
}
