package yainted.strategy;

import yainted.model.Player;
import yainted.model.TurnSnapshot;

import java.util.List;

/**
 * The ReportStrategy interface defines a method for generating game reports.
 */
public interface ReportStrategy {
    public void generateReport(List<TurnSnapshot> turnSnapshots, List<Player> players);
}