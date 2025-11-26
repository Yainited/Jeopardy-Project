package yainted;

public class SelectCategoryGameEvent extends GameEvent {
    public SelectCategoryGameEvent(String caseID, String playerID, String category) {
        super();
        this.caseID = caseID;
        this.playerID = playerID;
        this.activity = "Select Category";
        this.category = category;
    }
}