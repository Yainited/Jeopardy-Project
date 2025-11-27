import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yainted.events.*;
import yainted.model.Player;
import yainted.model.Question;
import yainted.observer.EventLogger;
import yainted.observer.EventManager;

import java.io.File;

import static org.junit.Assert.*;

public class EventLoggingTest {

    private Question question;
    private Player player;

    @BeforeEach
    public void setup() {
        player = new Player("John");
        player.addScore(500);
        question = new Question(
                "Math",
                100,
                "What is 2 + 2?",
                "4",
                null
        );

    }

    // Event Creation Tests

    @Test
    public void testAnswerQuestionGameEvent() {
        AnswerQuestionGameEvent event = new AnswerQuestionGameEvent(
                player,
                question,
                "4",
                "correct"
        );

        assertEquals("John", event.getPlayerID());
        assertEquals("Answer Question", event.getActivity());
        assertEquals("Math", event.getCategory());
        assertEquals("100", event.getQuestionValue());
        assertEquals("4", event.getAnswerGiven());
        assertEquals("correct", event.getResult());
        assertEquals("500", event.getScoreAfterPlay());
    }

    @Test
    public void testEnterPlayerNameGameEvent() {
        EnterPlayerNameGameEvent event = new EnterPlayerNameGameEvent("Alice");

        assertEquals("Alice", event.getPlayerID());
        assertEquals("Enter Player Name", event.getActivity());
        assertEquals("Alice", event.getAnswerGiven());
        assertNotNull(event.getTimestamp());
        assertNotNull(event.getCaseID());
    }

    @Test
    public void testExitGameEvent() {
        ExitGameEvent event = new ExitGameEvent();

        assertEquals("System", event.getPlayerID());
        assertEquals("Exit Game", event.getActivity());
        assertNotNull(event.getTimestamp());
        assertNotNull(event.getCaseID());
    }

    @Test
    void testGenerateEventLogGameEvent() {
        GenerateEventLogGameEvent event = new GenerateEventLogGameEvent();

        assertEquals("System", event.getPlayerID());
        assertEquals("Generate Event Log", event.getActivity());
        assertNotNull(event.getTimestamp());
        assertNotNull(event.getCaseID());
    }

    @Test
    void testGenerateReportEvent() {
        GenerateReportEvent event = new GenerateReportEvent();

        assertEquals("System", event.getPlayerID());
        assertEquals("Generate Report", event.getActivity());
        assertNotNull(event.getTimestamp());
        assertNotNull(event.getCaseID());
    }

    @Test
    void testLoadFileGameEvent() {
        LoadFileGameEvent event = new LoadFileGameEvent("Success");

        assertEquals("System", event.getPlayerID());
        assertEquals("Load File", event.getActivity());
        assertEquals("Success", event.getResult());
        assertNotNull(event.getTimestamp());
        assertNotNull(event.getCaseID());
    }

    @Test
    void testSelectPlayerCountGameEvent() {
        SelectPlayerCountGameEvent event = new SelectPlayerCountGameEvent(3);

        assertEquals("System", event.getPlayerID());
        assertEquals("Select Player Count", event.getActivity());
        assertEquals("3", event.getAnswerGiven());
        assertNotNull(event.getTimestamp());
        assertNotNull(event.getCaseID());
    }

    @Test
    void testSelectQuestionGameEvent() {
        SelectQuestionGameEvent event = new SelectQuestionGameEvent("John", question);

        assertEquals("John", event.getPlayerID());
        assertEquals("Select Question", event.getActivity());
        assertEquals("Math", event.getCategory());
        assertEquals("100", event.getQuestionValue());
        assertNotNull(event.getTimestamp());
        assertNotNull(event.getCaseID());
    }

    @Test
    void testStartGameEvent() {
        StartGameEvent event = new StartGameEvent();

        assertEquals("System", event.getPlayerID());
        assertEquals("Start Game", event.getActivity());
        assertNotNull(event.getTimestamp());
        assertNotNull(event.getCaseID());
    }

    // Event Manager Tests

    @Test
    void testLogEvent() {
        EventManager eventManager = EventManager.getInstance();
        eventManager.clearObservers();

        EventLogger eventLogger = new EventLogger();  // Create new, don't get from manager
        eventManager.attach(eventLogger);

        eventManager.notifyObservers(new StartGameEvent());

        assertEquals(1, eventLogger.getEventLog().size());
    }
    @Test
    void testLogMultipleEvents() {
        EventManager eventManager = EventManager.getInstance();
        eventManager.clearObservers();

        EventLogger eventLogger = new EventLogger();
        eventManager.attach(eventLogger);

        eventManager.notifyObservers(new StartGameEvent());
        eventManager.notifyObservers(new ExitGameEvent());

        assertEquals(2, eventLogger.getEventLog().size());
    }
    @Test
    void testGenerateEventLogCSV() {
        EventManager eventManager = EventManager.getInstance();
        eventManager.clearObservers();

        EventLogger eventLogger = new EventLogger();
        eventManager.attach(eventLogger);

        eventManager.notifyObservers(new StartGameEvent());

        eventLogger.generateEventLogCSV();

        assertEquals(1, eventLogger.getEventLog().size());

        File csvFile = new File("EVENT_LOG_" + eventManager.getCaseid() + ".csv");
        assertTrue(csvFile.exists());
        assertTrue(csvFile.length() > 0);

        csvFile.delete();
    }
}