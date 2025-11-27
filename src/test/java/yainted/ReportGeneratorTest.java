package yainted;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yainted.model.Player;
import yainted.model.Question;
import yainted.model.TurnSnapshot;
import yainted.observer.EventManager;
import yainted.strategy.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ReportGeneratorTest {
    private Question question;
    private Player player;
    private List<Player> players;

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
        players = new ArrayList<>();
        players.add(player);
    }
    @Test
    void testGeneratePDFReport() {
        List<TurnSnapshot> snapshots = new ArrayList<>();

        snapshots.add(new TurnSnapshot(1, player, question, "4", true));
        snapshots.add(new TurnSnapshot(2, player, question, "5", false));

        ReportContext ReportContext = new ReportContext(new PdfReportStrategy());
        ReportContext.generateReport(snapshots, players);

        File pdfFile = new File("REPORT_" + EventManager.getInstance().getCaseid() + ".pdf");
        assertTrue(pdfFile.exists());
        assertTrue(pdfFile.length() > 0);

        pdfFile.delete();
    }
    @Test
    void testGenerateDOCXReport() {
        List<TurnSnapshot> snapshots = new ArrayList<>();

        snapshots.add(new TurnSnapshot(1, player, question, "4", true));
        snapshots.add(new TurnSnapshot(2, player, question, "5", false));

        ReportContext ReportContext = new ReportContext(new DocxReportStrategy());
        ReportContext.generateReport(snapshots, players);

        File docxFile = new File("REPORT_" + EventManager.getInstance().getCaseid() + ".docx");
        assertTrue(docxFile.exists());
        assertTrue(docxFile.length() > 0);

        docxFile.delete();
    }
    @Test
    void testGenerateTXTReport() {
        List<TurnSnapshot> snapshots = new ArrayList<>();

        snapshots.add(new TurnSnapshot(1, player, question, "4", true));
        snapshots.add(new TurnSnapshot(2, player, question, "5", false));

        ReportContext ReportContext = new ReportContext(new TxtReportStrategy());
        ReportContext.generateReport(snapshots, players);

        File txtFile = new File("REPORT_" + EventManager.getInstance().getCaseid() + ".txt");
        assertTrue(txtFile.exists());
        assertTrue(txtFile.length() > 0);

        txtFile.delete();
    }
}
