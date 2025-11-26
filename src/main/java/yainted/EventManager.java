package yainted;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private List<GameObserver> observers;

    public EventManager() {
        this.observers = new ArrayList<>();
    }

    public void attach(GameObserver observer) {
        observers.add(observer);
    }
    public void detach(GameObserver observer) {
        observers.remove(observer);
    }
    public void notifyObservers(GameEvent gameEvent) {
        for (GameObserver observer : observers) {
            observer.update(gameEvent);
        }
    }
}