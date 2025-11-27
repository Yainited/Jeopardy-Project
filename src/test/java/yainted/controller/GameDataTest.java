package yainted.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import yainted.manager.GameData;
import yainted.manager.PlayerManager;
import yainted.manager.QuestionManager;
import yainted.model.Player;
import yainted.model.Question;
import yainted.model.TurnSnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GameDataTest {

    private GameData gameData;
    private PlayerManager playerManager;
    private QuestionManager questionManager;

    @BeforeEach
    void setUp() {
        playerManager = new PlayerManager();
        questionManager = new QuestionManager();
        gameData = new GameData(playerManager, questionManager);
    }

    @Test
    void testInitialState() {
        // Assert
        assertNull(gameData.getCurrentPlayer());
        assertNull(gameData.getCurrentQuestion());
        assertNotNull(gameData.getTurnHistory());
        assertTrue(gameData.getTurnHistory().isEmpty());
        assertEquals(playerManager, gameData.getPlayerManager());
        assertEquals(questionManager, gameData.getQuestionManager());
    }

    @Test
    void testSetAndGetCurrentPlayer() {
        
        Player player = new Player("Alice");

        gameData.setCurrentPlayer(player);

        assertEquals(player, gameData.getCurrentPlayer());
        assertEquals("Alice", gameData.getCurrentPlayer().getName());
    }

    @Test
    void testSetAndGetCurrentQuestion() {
    
        Map<String, String> map = new HashMap<>();
        map.put("A", "First");
        map.put("B", "Second");
        map.put("C", "Third");
        map.put("D", "Fourth");
        Question question = new Question("Category",200,"text","A",map);

        gameData.setCurrentQuestion(question);

        assertEquals(question, gameData.getCurrentQuestion());
        assertEquals("Category_200", gameData.getCurrentQuestion().getId());
    }

    @Test
    void testNextTurn() {
        
        playerManager.addPlayers(new ArrayList<>(Arrays.asList("Alice", "Bob")));
        Player alice = playerManager.getPlayerByName("Alice");
        Player bob = playerManager.getPlayerByName("Bob");
        
        gameData.setCurrentPlayer(alice);
        Map<String, String> map = new HashMap<>();
        map.put("A", "First");
        map.put("B", "Second");
        map.put("C", "Third");
        map.put("D", "Fourth");
        Question question = new Question("Science",200,"Hey","B",map);
        gameData.setCurrentQuestion(question);

        gameData.nextTurn();

        assertEquals(bob, gameData.getCurrentPlayer());
        assertNull(gameData.getCurrentQuestion());
    }

    @Test
    void testNextTurnWithSinglePlayer() {
        
        playerManager.addPlayers(new ArrayList<>(Arrays.asList("Alice")));
        Player alice = playerManager.getPlayerByName("Alice");
        gameData.setCurrentPlayer(alice);
        Map<String, String> map = new HashMap<>();
        map.put("A", "First");
        map.put("B", "Second");
        map.put("C", "Third");
        map.put("D", "Fourth");
        Question question = new Question("Science",200,"Hey","B",map);
        gameData.setCurrentQuestion(question);

        gameData.nextTurn();

        assertEquals(alice, gameData.getCurrentPlayer()); 
        assertNull(gameData.getCurrentQuestion());
    }

    @Test
    void testAddTurnSnapshot() {
        // Arrange
        Player player = new Player("Alice");
        Map<String, String> map = new HashMap<>();
        map.put("A", "First");
        map.put("B", "Second");
        map.put("C", "Third");
        map.put("D", "Fourth");
        Question question = new Question("Science", 200, "tets", "A", map);
        TurnSnapshot snapshot = new TurnSnapshot(1, player, question, "A", true);

        gameData.addTurnSnapshot(snapshot);

        assertEquals(1, gameData.getTurnHistory().size());
        assertEquals(snapshot, gameData.getTurnHistory().get(0));
    }

    @Test
    void testGetTurnHistory() {
        // Arrange
        Player player1 = new Player("Alice");
        Player player2 = new Player("Bob");
         Map<String, String> map = new HashMap<>();
        map.put("A", "First");
        map.put("B", "Second");
        map.put("C", "Third");
        map.put("D", "Fourth");
        Question question = new Question("Science", 200, "tets", "A", map);
        
        TurnSnapshot snapshot1 = new TurnSnapshot(1, player1, question, "A", true);
        TurnSnapshot snapshot2 = new TurnSnapshot(2, player2, question, "B", false);
        
        gameData.addTurnSnapshot(snapshot1);
        gameData.addTurnSnapshot(snapshot2);

        List<TurnSnapshot> history = gameData.getTurnHistory();

        assertEquals(2, history.size());
        assertEquals(snapshot1, history.get(0));
        assertEquals(snapshot2, history.get(1));
        assertEquals("Alice", history.get(0).getPlayerName());
        assertEquals("Bob", history.get(1).getPlayerName());
    }
}