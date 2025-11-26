package yainted;

public class GenerateReportGameEvent extends GameEvent {
    public GenerateReportGameEvent(String caseID, String result) {
        super();
        this.caseID = caseID;
        this.playerID = "System";
        this.activity = "Generate Report";
        this.result = result;
    }
}