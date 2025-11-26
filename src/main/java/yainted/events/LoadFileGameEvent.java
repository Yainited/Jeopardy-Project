package yainted.events;

public class LoadFileGameEvent extends GameEvent {
    public LoadFileGameEvent(String result) {
        super();
        this.playerID = "System";
        this.activity = "Load File";
        this.result = result;
    }
}