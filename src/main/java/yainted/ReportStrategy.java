package yainted;

import java.util.List;

public interface ReportStrategy {
    public void generateReport(List<TurnSnapshot> turnSnapshots, String filename);
}