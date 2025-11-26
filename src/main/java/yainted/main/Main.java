package yainted.main;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import yainted.events.StartGameEvent;
import yainted.game.GameController;
import yainted.observer.EventLogger;
import yainted.observer.EventManager;


public class Main {
    public static void main(String[] args) {
        // Create Event Manager
        EventManager eventManager = EventManager.getInstance();
        // Create Observer
        EventLogger eventLogger = new EventLogger();
        // Attach Observer
        eventManager.attach(eventLogger);

        GameController gameController = new GameController();
        // Notify
        EventManager.getInstance().notifyObservers(new StartGameEvent());

    }
}
