package yainted.observer;

import yainted.events.GameEvent;
import yainted.manager.PlayerManager;
import yainted.manager.QuestionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The EventManager class manages game events and notifies observers.
 */
public class EventManager {
    private String caseid;
    private PlayerManager playerManager;
    private QuestionManager questionManager;
    private static EventManager instance;
    private List<GameObserver> observers;

    public EventManager() {
        this.caseid = "GAME" + String.format("%03d", new Random().nextInt(1000));
        this.observers = new ArrayList<>();
    }

    // Singleton
    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
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

    public EventLogger getEventLogger() {
        for (GameObserver observer : observers) {
            if (observer instanceof EventLogger) {
                return (EventLogger) observer;
            }
        }
        return null;
    }

    public void setCaseid(String caseid) {
        this.caseid = caseid;
    }

    public String getCaseid() {
        return caseid;
    }

    public void clearObservers() {
        observers.clear();
    }
}