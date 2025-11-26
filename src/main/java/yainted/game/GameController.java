package yainted.game;
import yainted.events.GenerateReportEvent;
import yainted.events.LoadFileGameEvent;
import yainted.events.StartGameEvent;
import yainted.gui.GameView;
import java.util.ArrayList;
import yainted.model.Question;
import yainted.observer.EventManager;
import yainted.strategy.DocxReportStrategy;
import yainted.strategy.PdfReportStrategy;
import yainted.strategy.ReportContext;
import yainted.strategy.TxtReportStrategy;

/**
 * The GameController class manages the interactions between the model and view.
 */
public class GameController {
    private GameLogic gameLogic;
    private GameView gameView;

    public GameController() {
        this.gameView = new GameView(this);
    }

    /**
     * Sends the file path while notifying observers with a LoadFileGameEvent.
     */
    public void getFilePath(String path)
    {
        ArrayList<Question> questions = DataInitializer.getQuestions(path);
        if (questions.isEmpty()) {
            EventManager.getInstance().notifyObservers(new LoadFileGameEvent("Failed"));
            throw new IllegalArgumentException("No questions loaded from the file: " + path);
        }
        this.gameLogic = new GameLogic(questions);
        EventManager.getInstance().notifyObservers(new LoadFileGameEvent("Success"));
    }

    /**
     * Sends the player names to the game logic.
     */
    public void getPlayers(java.util.ArrayList<String> players)
    {
        this.gameLogic.setPlayers(players);
    }

    /** Retrieves the list of players from the game logic.
     * @return The list of players.
     */
    public ArrayList<Player> sendPlayersToView()
    {
        return this.gameLogic.getPlayers();
    }
    /** Selects a question based on value and category.
     * @param value The value of the question.
     * @param category The category of the question.
     * @return The selected Question object.
     */
    public Question selectQuestion(String value, String category)
    {
        String id = category + "_" + value;
        this.gameLogic.PlayerChooseQuestion(id);
        return this.gameLogic.getQuestionByID(id);
    }
    /** Retrieves the current question.
     * @return The current Question object.
     */
    public Question getCurrentQuestion()
    {
        return this.gameLogic.getCurrentQuestion();
    }

    /** Sets the report format for the game.
     * @param format The report format to set.
     */
    public void setReportFormat(String format)
    {
        String reportFormat = format.toUpperCase();
        ReportContext reportContext;
        switch (reportFormat) {
            case "TXT":
                reportContext = new ReportContext(new TxtReportStrategy());
                reportContext.generateReport(gameLogic.getTurnHistory(), gameLogic.getPlayers());
                break;
            case "PDF":
                reportContext = new ReportContext(new PdfReportStrategy());
                reportContext.generateReport(gameLogic.getTurnHistory(), gameLogic.getPlayers());
                break;
            case "DOCX":
                reportContext = new ReportContext(new DocxReportStrategy());
                reportContext.generateReport(gameLogic.getTurnHistory(), gameLogic.getPlayers());
                break;
        }

    }
    /** Validates the given answer.
     * @param ans The answer to validate.
     * @return True if the answer is correct, false otherwise.
     */
    public Boolean validateAnswer(String ans)
    {
        Boolean test =  this.gameLogic.AnswerQuestion(ans);
        return test;
    }
}
