package yainted.controller;

import yainted.manager.GameData;
import yainted.strategy.DocxReportStrategy;
import yainted.strategy.PdfReportStrategy;
import yainted.strategy.ReportContext;
import yainted.strategy.TxtReportStrategy;

public class ReportController {
    
    private GameData gameData;

    public ReportController(GameData gameData) {
        this.gameData = gameData;
    }

    /** Sets the report format for the game.
     * @param format The report format to set.
     */
    public void setReportFormat(String format)
    {
        String reportFormat = format.toUpperCase();
        ReportContext reportContext;
        switch (reportFormat) {
            case "TXT":
                reportContext = new ReportContext(new TxtReportStrategy());
                reportContext.generateReport(gameData.getTurnHistory(), gameData.getPlayerManager().getPlayers());
                break;
            case "PDF":
                reportContext = new ReportContext(new PdfReportStrategy());
                reportContext.generateReport(gameData.getTurnHistory(), gameData.getPlayerManager().getPlayers());
                break;
            case "DOCX":
                reportContext = new ReportContext(new DocxReportStrategy());
                reportContext.generateReport(gameData.getTurnHistory(), gameData.getPlayerManager().getPlayers());
                break;
        }

    }
}
