package yainted.events;

public class StartGameEvent extends GameEvent {
    public StartGameEvent() {
        super();
        this.playerID = "System";
        this.activity = "Start Game";
    }
}