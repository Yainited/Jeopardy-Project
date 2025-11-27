package yainted.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import yainted.controller.BoardController;
import yainted.manager.GameData;
import yainted.manager.PlayerManager;
import yainted.manager.QuestionManager;
import yainted.model.Category;
import yainted.model.Player;
import yainted.model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BoardControllerTest {

    private GameData gd;
    private BoardController bc;
    private PlayerManager pm;
    private QuestionManager qm;

    @BeforeEach
    void setUp() {
        pm = new PlayerManager();
        qm = new QuestionManager();
        gd = new GameData(pm, qm);
        bc = new BoardController(gd);
    }

    @Test
    void testGetPlayers() {
        
        pm.addPlayers(new ArrayList<>(Arrays.asList("Alice", "Bob")));

        ArrayList<Player> result = bc.getPlayers();

        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getName());
        assertEquals("Bob", result.get(1).getName());
    }

    @Test
    void testSelectQuestion() {
    
        int value = 100;
        String categoryName = "Science";
        String ID = categoryName + "_" + value;
        
        Category category = new Category(categoryName);
        Map<String, String> map = new HashMap<>();
        map.put("A", "First");
        map.put("B", "Second");
        map.put("C", "Third");
        map.put("D", "Fourth");
        Question question = new Question(categoryName, value, "Test", "A", map);
        category.addQuestion(question);
        qm.addCategory(category);
        
        Player player = new Player("P1");
        gd.setCurrentPlayer(player);

        bc.selectQuestion(String.valueOf(value), categoryName);

        Question currentQuestion = gd.getCurrentQuestion();
        assertNotNull(currentQuestion);
        assertEquals(ID, currentQuestion.getId());
    }

    @Test
    void testGetCategoryNames() {
        
        Category scienceCategory = new Category("Science");
        Category historyCategory = new Category("History");
        qm.addCategory(scienceCategory);
        qm.addCategory(historyCategory);

        ArrayList<String> result = bc.getCategoryNames();

        assertEquals(2, result.size());
        assertTrue(result.contains("Science"));
        assertTrue(result.contains("History"));
    }

    @Test
    void testIsGameOver() {
        
        Category category = new Category("Science");
        String categoryName = "Science";
        int value1 = 200;
        int value2 = 300;
        
        Map<String, String> map = new HashMap<>();
        map.put("A", "First");
        map.put("B", "Second");
        map.put("C", "Third");
        map.put("D", "Fourth");
        Question question1 = new Question(categoryName, value1, "Test", "A", map);
        Question question2 = new Question(categoryName, value2, "Test", "A", map);
        category.addQuestion(question1);
        category.addQuestion(question2);
        qm.addCategory(category);
        
        qm.incrementAnsweredQuestions();
        qm.incrementAnsweredQuestions();

        boolean result = bc.isGameOver();

        assertTrue(result);
    }

    @Test
    void testIsGameNotOver() {

        Category category = new Category("Science");
        String categoryName = "Science";
        int value1 = 200;
        int value2 = 300;
        
        Map<String, String> map = new HashMap<>();
        map.put("A", "First");
        map.put("B", "Second");
        map.put("C", "Third");
        map.put("D", "Fourth");
        Question question1 = new Question(categoryName, value1, "Test", "A", map);
        Question question2 = new Question(categoryName, value2, "Test", "A", map);
        category.addQuestion(question1);
        category.addQuestion(question2);
        qm.addCategory(category);
        
        qm.incrementAnsweredQuestions();

        boolean result = bc.isGameOver();

        assertFalse(result);
    }

    @Test
    void testGetCurrentPlayer() {
        
        Player player = new Player("Alice");
        gd.setCurrentPlayer(player);

        Player result = bc.getCurrentPlayer();

        assertEquals(player, result);
        assertEquals("Alice", result.getName());
    }
}