package yainted.main;

import yainted.gui.CreatePlayers;
import yainted.gui.FileChooser;
import yainted.gui.GameScreen;
import yainted.gui.GameView;
import yainted.gui.SelectedQuestion;
import yainted.gui.SummaryScreen;
import yainted.controller.BoardController;
import yainted.controller.QuestionController;
import yainted.controller.ReportController;
import yainted.controller.SetupController;
import yainted.events.StartGameEvent;
import yainted.manager.GameData;
import yainted.manager.PlayerManager;
import yainted.manager.QuestionManager;
import yainted.observer.EventLogger;
import yainted.observer.EventManager;


public class Main {
    public static void main(String[] args) {
        // Create Event Manager
        EventManager eventManager = EventManager.getInstance();
        // Create Observer
        EventLogger eventLogger = new EventLogger();
        // Attach Observer
        eventManager.attach(eventLogger);

        PlayerManager playerManager = new PlayerManager();
        QuestionManager questionManager = new QuestionManager();
        GameData gameData = new GameData(playerManager, questionManager);

        BoardController boardController = new BoardController(gameData);
        QuestionController questionController = new QuestionController(gameData);
        SetupController setupController = new SetupController(gameData);
        ReportController reportController = new ReportController(gameData);

        FileChooser fileChooser = new FileChooser(setupController);
        CreatePlayers createPlayers = new CreatePlayers(setupController);
        GameScreen gameScreen = new GameScreen(boardController);
        SelectedQuestion selectedQuestion = new SelectedQuestion(questionController);
        SummaryScreen summaryScreen = new SummaryScreen(reportController);

        GameView mainFrame = new GameView(gameScreen, fileChooser, createPlayers, selectedQuestion, summaryScreen);

        // Notify
        EventManager.getInstance().notifyObservers(new StartGameEvent());

    }
}
