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
            EventManager.getInstance().notifyObservers(new LoadFileGameEvent("Failed"));
            throw new IllegalArgumentException("No questions loaded from the file: " + path);
        }
        this.gameLogic = new GameLogic(questions);
        EventManager.getInstance().notifyObservers(new LoadFileGameEvent("Success"));
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
    public Boolean validateAnswer(String ans)
    {
        Boolean test =  this.gameLogic.AnswerQuestion(ans);
        return test;
    }
}
