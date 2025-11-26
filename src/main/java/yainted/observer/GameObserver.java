package yainted.observer;

import yainted.events.GameEvent;

public interface GameObserver  {
    public void update(GameEvent gameEvent);
}