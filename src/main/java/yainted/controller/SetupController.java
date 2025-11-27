package yainted.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import yainted.events.EnterPlayerNameGameEvent;
import yainted.events.LoadFileGameEvent;
import yainted.manager.GameData;
import yainted.manager.PlayerManager;
import yainted.manager.QuestionManager;
import yainted.model.Category;
import yainted.model.Player;
import yainted.model.Question;
import yainted.observer.EventManager;
import yainted.parser.QuestionParser;
import yainted.parser.QuestionParserFactory;

/**
 * Controller for handling the Setup and CreatePlayers Panel.
 */
public class SetupController {
    private GameData gameData;

    public SetupController(GameData gameData) {
        this.gameData = gameData;
    }

    /** Receives the file path from the GUI and loads questions into the game data.
     * @param path The file path of the question file.
     */
    public void receiveFilePath(String path)
    {
        List<Question> questions = readQuestions(path);
        if (questions.isEmpty()) {
            EventManager.getInstance().notifyObservers(new LoadFileGameEvent("Failed"));
            throw new IllegalArgumentException("No questions loaded from the file: " + path);
        }
        loadQuestionsToGameData(questions);
        EventManager.getInstance().notifyObservers(new LoadFileGameEvent("Success"));
    }

    /** Reads questions from the specified file path.
     * @param filePath The file path of the question file.
     * @return A list of Question objects.
     */
    public List<Question> readQuestions(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist: " + filePath);
        }

        try {
            QuestionParser parser = QuestionParserFactory.getParser(filePath);
            return parser.parse(file);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing questions from file: " + filePath, e);
        }
    }

    /** Loads questions into the game data's question manager.
     * @param questions The list of Question objects to load.
     */
    public void loadQuestionsToGameData(List<Question> questions) {
        QuestionManager qm = gameData.getQuestionManager();
        for (Question question : questions) {
            String categoryName = question.getCategory();
            Category category = qm.getCategories().get(categoryName);
            if (category == null) {
                category = new Category(categoryName);
                qm.addCategory(category);
            }
            category.addQuestion(question);
        }
    }

    /** Receives player names from the GUI and adds them to the game data.
     * @param playerNames The list of player names.
     */
    public void recievePlayerNames(List<String> playerNames)
    {
        PlayerManager pm = gameData.getPlayerManager();
        pm.addPlayers(new ArrayList<>(playerNames));
        Player firstPlayer = pm.getPlayers().get(0);
        gameData.setCurrentPlayer(firstPlayer);
        for (String name : playerNames) {
            EventManager.getInstance().notifyObservers(new EnterPlayerNameGameEvent(name));
        }
    }
}
