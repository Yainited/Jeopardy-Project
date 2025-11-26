package yainted.observer;

import yainted.events.GameEvent;
import yainted.events.GenerateEventLogGameEvent;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * The EventLogger class logs game events and generates a CSV log file.
 */
public class EventLogger implements GameObserver {
    private List<GameEvent> eventLog;

    public EventLogger() {
        this.eventLog = new ArrayList<>();
    }

    /** Updates the event log with a new game event.
     * @param gameEvent The game event to log.
     */
    @Override
    public void update(GameEvent gameEvent) {
        this.eventLog.add(gameEvent);
    }

    /** Formats a game event as a CSV string.
     * @param event The game event to format.
     * @return A CSV-formatted string representing the event.
     */
    public String formatEventAsCSV(GameEvent event) {
        return String.join(",",
                event.getCaseID(),
                event.getPlayerID(),
                event.getActivity(),
                event.getTimestamp(),
                event.getCategory(),
                event.getQuestionValue(),
                event.getAnswerGiven(),
                event.getResult(),
                event.getScoreAfterPlay()
        );
    }

    /** Generates a CSV file containing the event log. */
    public void generateEventLogCSV() {
        String filename = "EVENT_LOG_" + eventLog.get(0).getCaseID() + ".csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Case_ID,Player_ID,Activity,Timestamp,Category,Question_Value,Answer_Given,Result,Score_After_Play");
            for (GameEvent gameEvent : this.eventLog) {
                writer.println(formatEventAsCSV(gameEvent));
            }
            System.out.println("Event log generated: " + filename);

        } catch (IOException e) {
            System.err.println("Error generating event log: " + e.getMessage());
        }
    }
}