package yainted.strategy;

import yainted.events.GenerateReportEvent;
import yainted.model.Player;
import yainted.model.TurnSnapshot;
import yainted.observer.EventManager;

import java.util.List;

/**
 * The ReportContext class uses a ReportStrategy to generate game reports.
 */
public class ReportContext {
    private ReportStrategy strategy;

    public ReportContext(ReportStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(ReportStrategy strategy) {
        this.strategy = strategy;
    }
    public void generateReport(List<TurnSnapshot> turnSnapshots, List<Player> players) {
        strategy.generateReport(turnSnapshots, players);
        EventManager.getInstance().notifyObservers(new GenerateReportEvent());
    }
}