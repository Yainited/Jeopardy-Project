package yainted.events;
/**
 * Represents a game event for when a report is generated.
 * Records the report generation activity with the player ID set to "System".
 */
public class GenerateReportEvent extends GameEvent {
    public GenerateReportEvent() {
        super();
        this.playerID = "System";
        this.activity = "Generate Report";
    }
}