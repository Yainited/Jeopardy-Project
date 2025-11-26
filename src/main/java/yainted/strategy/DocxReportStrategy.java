package yainted.strategy;

import yainted.game.Player;
import yainted.model.TurnSnapshot;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xwpf.usermodel.*;
import yainted.observer.EventManager;

/**
 * The DocxReportStrategy class generates a DOCX report of the game.
 */
public class DocxReportStrategy implements ReportStrategy {

    /** Generates a DOCX report of the game.
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
        String filename = "REPORT_" + caseID + ".docx";

        try (XWPFDocument document = new XWPFDocument();
             FileOutputStream out = new FileOutputStream(filename)) {

            XWPFParagraph title = document.createParagraph();
            title.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun = title.createRun();
            titleRun.setText("JEOPARDY PROGRAMMING GAME REPORT");
            titleRun.setBold(true);
            titleRun.setFontSize(18);

            document.createParagraph();

            XWPFParagraph caseIdPara = document.createParagraph();
            caseIdPara.createRun().setText("Case ID: " + caseID);

            document.createParagraph();

            XWPFParagraph playersPara = document.createParagraph();
            playersPara.createRun().setText("Players: " + String.join(", ", playerNames));

            document.createParagraph();

            XWPFParagraph summaryHeader = document.createParagraph();
            XWPFRun summaryRun = summaryHeader.createRun();
            summaryRun.setText("Gameplay Summary:");
            summaryRun.setBold(true);
            summaryRun.setFontSize(14);

            for (TurnSnapshot turn : turnSnapshots) {
                XWPFParagraph turnPara = document.createParagraph();
                turnPara.createRun().setText("Turn " + turn.getTurnNumber() + ": " +
                        turn.getPlayerName() + " selected " + turn.getCategory() +
                        " for " + turn.getQuestionValue() + " pts");

                XWPFParagraph questionPara = document.createParagraph();
                questionPara.createRun().setText("Question: " + turn.getQuestion());

                XWPFParagraph answerPara = document.createParagraph();
                answerPara.createRun().setText("Answer: " + turn.getAnswerGiven() + " — " +
                        (turn.isCorrect() ? "Correct" : "Incorrect") + " (" +
                        (turn.isCorrect() ? "+" : "") + turn.getQuestionValue() + " pts)");

                XWPFParagraph scorePara = document.createParagraph();
                scorePara.createRun().setText("Score after turn: " + turn.getPlayerName() +
                        " = " + turn.getCurrentScore());

                document.createParagraph();
            }

            XWPFParagraph scoresHeader = document.createParagraph();
            XWPFRun scoresRun = scoresHeader.createRun();
            scoresRun.setText("Final Scores:");
            scoresRun.setBold(true);
            scoresRun.setFontSize(14);

            for (Player player : players) {
                XWPFParagraph scorePara = document.createParagraph();
                scorePara.createRun().setText(player.getName() + ": " + player.getScore());
            }

            document.write(out);
            System.out.println("DOCX report generated: " + filename);

        } catch (Exception e) {
            System.err.println("Error generating DOCX report: " + e.getMessage());
        }
    }
}