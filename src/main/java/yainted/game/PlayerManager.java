package yainted.game;

import yainted.events.EnterPlayerNameGameEvent;
import yainted.events.SelectPlayerCountGameEvent;
import yainted.observer.EventManager;

import java.util.ArrayList;

/**
 * Manages the players in the game.
 */
public class PlayerManager {

    private ArrayList<Player> players;
    private int index = 0;
    private Player currentPlayer;

    public PlayerManager(ArrayList<String> playersName) {
        this.players = new ArrayList<>();
        for (String name : playersName) {
            this.players.add(new Player(name));
        }
        currentPlayer = this.players.get(0);
    }
    /** Retrieves the list of players.
     * @return The list of players.
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }
    /** Retrieves the current player.
     * @return The current Player object.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /** Advances to the next player's turn. */
    public void advanceTurn() {
        index = (index + 1) % players.size();
        currentPlayer = players.get(index);
    }
}
