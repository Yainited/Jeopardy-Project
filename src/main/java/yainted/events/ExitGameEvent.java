package yainted.events;

public class ExitGameEvent extends GameEvent {
    public ExitGameEvent() {
        super();
        this.playerID = "System";
        this.activity = "Exit Game";
    }
}