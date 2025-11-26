package yainted.observer;

import yainted.events.GameEvent;
import yainted.events.GenerateEventLogGameEvent;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class EventLogger implements GameObserver {
    private List<GameEvent> eventLog;

    public EventLogger() {
        this.eventLog = new ArrayList<>();
    }

    @Override
    public void update(GameEvent gameEvent) {
        this.eventLog.add(gameEvent);
    }

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