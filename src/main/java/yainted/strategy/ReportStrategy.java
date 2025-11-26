package yainted.strategy;

import yainted.TurnSnapshot;

import java.util.List;

public interface ReportStrategy {
    public void generateReport(List<TurnSnapshot> turnSnapshots, String filename);
}