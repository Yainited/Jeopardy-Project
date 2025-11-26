package yainted;

public class GenerateReportEvent extends GameEvent {
    public GenerateReportEvent(String caseID) {
        super();
        this.caseID = caseID;
        this.playerID = "System";
        this.activity = "Generate Report";
    }
}