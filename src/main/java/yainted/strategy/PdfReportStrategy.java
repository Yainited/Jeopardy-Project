package yainted.strategy;

import yainted.game.Player;
import yainted.model.TurnSnapshot;

import java.util.ArrayList;
import java.util.List;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import yainted.observer.EventManager;

import java.io.FileOutputStream;

/**
 * The PdfReportStrategy class generates a PDF report of the game.
 */
public class PdfReportStrategy implements ReportStrategy {

    /** Generates a PDF report of the game.
     * @param turnSnapshots The list of turn snapshots.
     * @param players The list of players.
     */
    @Override
    public void generateReport(List<TurnSnapshot> turnSnapshots, List<Player> players) {
        List<String> playerNames = new ArrayList<>();
        for (Player player : players) {
            playerNames.add(player.getName());
        }
        String caseID = EventManager.getInstance().getCaseid();
        String filename = "REPORT_" + caseID + ".pdf";

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

            Paragraph title = new Paragraph("JEOPARDY PROGRAMMING GAME REPORT", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Case ID: " + caseID, normalFont));
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Players: " + String.join(", ", playerNames), normalFont));
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Gameplay Summary:", headerFont));
            document.add(new Paragraph("\n"));

            for (TurnSnapshot turn : turnSnapshots) {
                document.add(new Paragraph("Turn " + turn.getTurnNumber() + ": " +
                        turn.getPlayerName() + " selected " + turn.getCategory() +
                        " for " + turn.getQuestionValue() + " pts", normalFont));
                document.add(new Paragraph("Question: " + turn.getQuestion(), normalFont));
                document.add(new Paragraph("Answer: " + turn.getAnswerGiven() + " — " +
                        (turn.isCorrect() ? "Correct" : "Incorrect") + " (" +
                        (turn.isCorrect() ? "+" : "") + turn.getQuestionValue() + " pts)", normalFont));
                document.add(new Paragraph("Score after turn: " + turn.getPlayerName() +
                        " = " + turn.getCurrentScore(), normalFont));
                document.add(new Paragraph("\n"));
            }

            document.add(new Paragraph("Final Scores:", headerFont));
            for (Player player : players) {
                document.add(new Paragraph(player.getName() + ": " + player.getScore(), normalFont));
            }

            document.close();
            System.out.println("PDF report generated: " + filename);

        } catch (Exception e) {
            System.err.println("Error generating PDF report: " + e.getMessage());
        }
    }
}
