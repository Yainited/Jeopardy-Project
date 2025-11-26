package yainted.observer;

import yainted.events.GameEvent;

/**
 * The GameObserver interface defines a method for receiving game event updates.
 */
public interface GameObserver  {
    public void update(GameEvent gameEvent);
}