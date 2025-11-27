package yainted.strategy;

import yainted.events.GenerateReportEvent;
import yainted.model.Player;
import yainted.model.TurnSnapshot;
import yainted.observer.EventManager;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * The TxtReportStrategy class generates a TXT report of the game.
 */
public class TxtReportStrategy implements ReportStrategy {

    @Override
    public void generateReport(List<TurnSnapshot> turnSnapshots,  List<Player> players) {
        List<String> playerNames = new ArrayList<>();
        for (Player player : players) {
            playerNames.add(player.getName());
        }
        String caseID = EventManager.getInstance().getCaseid();
        String filename = "REPORT_" + caseID + ".txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {

            writer.println("JEOPARDY PROGRAMMING GAME REPORT");
            writer.println("================================");
            writer.println();
            writer.println("Case ID: " + caseID);
            writer.println();
            writer.println("Players: " + String.join(", ", playerNames));
            writer.println();
            writer.println("Gameplay Summary:");
            writer.println("-----------------");

            for (TurnSnapshot turn : turnSnapshots) {
                writer.println("Turn " + turn.getTurnNumber() + ": " +
                        turn.getPlayerName() + " selected " +
                        turn.getCategory() + " for " +
                        turn.getQuestionValue() + " pts");
                writer.println("Question: " + turn.getQuestion());
                writer.println("Answer: " + turn.getAnswerGiven() + " — " +
                        (turn.isCorrect() ? "Correct" : "Incorrect") +
                        " (" + (turn.isCorrect() ? "+" : "") +
                        turn.getQuestionValue() + " pts)");
                writer.println("Score after turn: " + turn.getPlayerName() +
                        " = " + turn.getCurrentScore());
                writer.println();
            }

            writer.println("Final Scores:");
            for (Player player : players) {
                writer.println(player.getName() + ": " + player.getScore() );
            }

            System.out.println("TXT report generated: " + filename);

        } catch (IOException e) {
            System.err.println("Error generating TXT report: " + e.getMessage());
        }
    }
}
