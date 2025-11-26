package yainted.events;

public class GenerateReportEvent extends GameEvent {
    public GenerateReportEvent() {
        super();
        this.playerID = "System";
        this.activity = "Generate Report";
    }
}