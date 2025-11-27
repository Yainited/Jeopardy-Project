package yainted.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import yainted.controller.QuestionController;
import yainted.manager.GameData;
import yainted.manager.PlayerManager;
import yainted.manager.QuestionManager;
import yainted.model.Player;
import yainted.model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class QuestionControllerTest {

    private GameData gameData;
    private QuestionController questionController;
    private PlayerManager playerManager;
    private QuestionManager questionManager;

    @BeforeEach
    void setUp() {
        playerManager = new PlayerManager();
        questionManager = new QuestionManager();
        gameData = new GameData(playerManager, questionManager);
        questionController = new QuestionController(gameData);
    }

    @Test
    void testGetCurrentQuestion() {
        // Arrange
        Map<String, String> map = new HashMap<>();
        map.put("A", "First");
        map.put("B", "Second");
        map.put("C", "Third");
        map.put("D", "Fourth");
        Question question = new Question("Science",100,"test","A",map);
        gameData.setCurrentQuestion(question);

        // Act
        Question result = questionController.getCurrentQuestion();

        // Assert
        assertEquals(question, result);
        assertEquals("Science_100", result.getId());
    }

    @Test
    void testValidateAnswerCorrect() {
        
        Player player = new Player("Alice");
        playerManager.addPlayer(player);
        gameData.setCurrentPlayer(player);
        
        Map<String, String> map = new HashMap<>();
        map.put("A", "First");
        map.put("B", "Second");
        map.put("C", "Third");
        map.put("D", "Fourth");
        Question question = new Question("Science",100,"ask","B",map);
        gameData.setCurrentQuestion(question);
        
        int initialScore = player.getScore();
        int initialAnsweredQuestions = questionManager.getAnsweredQuestions();

        // Act
        Boolean result = questionController.validateAnswer("B");

        // Assert
        assertTrue(result);
        assertEquals(initialScore + 100, player.getScore());
        assertEquals(initialAnsweredQuestions + 1, questionManager.getAnsweredQuestions());
        assertNotNull(gameData.getTurnHistory());
        assertEquals(1, gameData.getTurnHistory().size());
    }

    @Test
    void testValidateAnswerIncorrect() {
        
        Player player = new Player("Bob");
        playerManager.addPlayer(player);
        gameData.setCurrentPlayer(player);
        
        Map<String, String> map = new HashMap<>();
        map.put("A", "First");
        map.put("B", "Second");
        map.put("C", "Third");
        map.put("D", "Fourth");
       Question question = new Question("Science",100,"ask","B",map);
        gameData.setCurrentQuestion(question);
        
        int initialScore = player.getScore();
        int initialAnsweredQuestions = questionManager.getAnsweredQuestions();

        Boolean result = questionController.validateAnswer("A");

        assertFalse(result);
        assertEquals(initialScore - 100, player.getScore());
        assertEquals(initialAnsweredQuestions + 1, questionManager.getAnsweredQuestions());
        assertNotNull(gameData.getTurnHistory());
        assertEquals(1, gameData.getTurnHistory().size());
    }

    @Test
    void testValidateAnswerUpdatesCurrentPlayer() {
        
        playerManager.addPlayers(new ArrayList<>(Arrays.asList("Alice", "Bob")));
        Player alice = playerManager.getPlayerByName("Alice");
        Player bob = playerManager.getPlayerByName("Bob");
        
        gameData.setCurrentPlayer(alice);
        Map<String, String> map = new HashMap<>();
        map.put("A", "First");
        map.put("B", "Second");
        map.put("C", "Third");
        map.put("D", "Fourth");
        Question question = new Question("Science",100,"ask","B",map);
        gameData.setCurrentQuestion(question);

        questionController.validateAnswer("C");

        assertEquals(bob, gameData.getCurrentPlayer());
        assertNull(gameData.getCurrentQuestion()); 
    }
}