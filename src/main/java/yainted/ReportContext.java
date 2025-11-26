package yainted;

import java.util.List;

public class ReportContext {
    private ReportStrategy strategy;

    public ReportContext(ReportStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(ReportStrategy strategy) {
        this.strategy = strategy;
    }
    public void generateReport(List<TurnSnapshot> turnSnapshots, String filename) {
        strategy.generateReport(turnSnapshots, filename);
    }
}