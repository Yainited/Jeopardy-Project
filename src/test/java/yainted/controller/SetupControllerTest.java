package yainted.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yainted.manager.GameData;
import yainted.manager.PlayerManager;
import yainted.manager.QuestionManager;
import yainted.model.Category;
import yainted.model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SetupControllerTest {

    private GameData gameData;
    private SetupController setupController;
    private PlayerManager playerManager;
    private QuestionManager questionManager;

    @BeforeEach
    void setUp() {
        playerManager = new PlayerManager();
        questionManager = new QuestionManager();
        gameData = new GameData(playerManager, questionManager);
        setupController = new SetupController(gameData);
    }

    @Test
    void testRecievePlayerNames() {
        
        List<String> playerNames = Arrays.asList("Alice", "Bob", "Charlie");

        setupController.recievePlayerNames(playerNames);

        assertEquals(3, playerManager.getPlayers().size());
        assertEquals("Alice", playerManager.getPlayers().get(0).getName());
        assertEquals("Bob", playerManager.getPlayers().get(1).getName());
        assertEquals("Charlie", playerManager.getPlayers().get(2).getName());
        assertNotNull(gameData.getCurrentPlayer());
        assertEquals("Alice", gameData.getCurrentPlayer().getName());
    }

    @Test
    void testLoadQuestionsToGameData() {
        
        Map<String, String> map = new HashMap<>();
        map.put("A", "First");
        map.put("B", "Second");
        map.put("C", "Third");
        map.put("D", "Fourth");
        Question question1 = new Question("Science",100,"ask","B",map);
        Question question2 = new Question("History",200,"ask","C",map);
        List<Question> questions = new ArrayList<>();
        questions.add(question2);
        questions.add(question1);

        setupController.loadQuestionsToGameData(questions);

        assertEquals(2, questionManager.getCategories().size());
        assertTrue(questionManager.getCategories().containsKey("Science"));
        assertTrue(questionManager.getCategories().containsKey("History"));
        
        Category scienceCategory = questionManager.getCategories().get("Science");
        Category historyCategory = questionManager.getCategories().get("History");
        
        assertEquals(1, scienceCategory.getQuestions().size());
        assertEquals(1, historyCategory.getQuestions().size());
        assertEquals(question1, scienceCategory.getQuestions().get(0));
        assertEquals(question2, historyCategory.getQuestions().get(0));
    }

    @Test
    void testLoadQuestionsToGameDataExistingCategory() {
        
        Category existingScienceCategory = new Category("Science");
        questionManager.addCategory(existingScienceCategory);
        
        Map<String, String> map = new HashMap<>();
        map.put("A", "First");
        map.put("B", "Second");
        map.put("C", "Third");
        map.put("D", "Fourth");
        Question question1 = new Question("Science",100,"ask","B",map);
        List<Question> questions = new ArrayList<>();
        questions.add(question1);

        setupController.loadQuestionsToGameData(questions);

        assertEquals(1, questionManager.getCategories().size());
        Category scienceCategory = questionManager.getCategories().get("Science");
        assertEquals(1, scienceCategory.getQuestions().size());
        assertEquals(question1, scienceCategory.getQuestions().get(0));
    }

    @Test
    void testLoadQuestionsToGameDataMultipleQuestionsSameCategory() {
        
        Map<String, String> map = new HashMap<>();
        map.put("A", "First");
        map.put("B", "Second");
        map.put("C", "Third");
        map.put("D", "Fourth");

        Question question1 = new Question("Science",100,"ask","B",map);
        Question question2 = new Question("Science",200,"ask","B",map);
        Question question3 = new Question("History",100,"ask","B",map);

        List<Question> questions = new ArrayList<>();
        questions.add(question3);
        questions.add(question2);
        questions.add(question1);

        
        setupController.loadQuestionsToGameData(questions);

        
        assertEquals(2, questionManager.getCategories().size());
        
        Category scienceCategory = questionManager.getCategories().get("Science");
        Category historyCategory = questionManager.getCategories().get("History");
        
        assertEquals(2, scienceCategory.getQuestions().size());
        assertEquals(1, historyCategory.getQuestions().size());
    }
}