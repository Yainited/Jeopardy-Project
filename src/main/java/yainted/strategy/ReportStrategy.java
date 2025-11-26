package yainted.strategy;

import yainted.model.TurnSnapshot;

import java.util.List;

public interface ReportStrategy {
    public void generateReport(List<TurnSnapshot> turnSnapshots, String filename);
}