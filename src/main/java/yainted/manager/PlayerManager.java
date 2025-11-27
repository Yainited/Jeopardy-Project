package yainted.manager;

import yainted.events.EnterPlayerNameGameEvent;
import yainted.events.SelectPlayerCountGameEvent;
import yainted.model.Player;
import yainted.observer.EventManager;

import java.util.ArrayList;

/**
 * Manages the players in the game.
 */
public class PlayerManager {

    private ArrayList<Player> players = new ArrayList<>();
    
    /** Retrieves the list of players.
     * @return The list of players.
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }
    /** Retrieves a player by their name.
     * @param name The name of the player.
     * @return The Player object with the specified name, or null if not found.
     */
    public Player getPlayerByName(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }
    /** Adds a player to the game.
     * @param player The player to add.
     */
    public void addPlayer(Player player) {
        players.add(player);
    }
    /** Adds multiple players to the game with an ArrayList of names.
     * @param playerNames The list of player names to add.
     */
    public void addPlayers(ArrayList<String> playerNames) {
        for (String name : playerNames) {
            players.add(new Player(name));
        }
    }
    /** Retrieves the next player in turn order.
     * @param currentPlayer The current player.
     * @return The next Player object.
     */
    public Player getNextPlayer(Player currentPlayer) {
        if (players.isEmpty()) {
            return null;
        }
        int currentIndex = players.indexOf(currentPlayer);
        int nextIndex = (currentIndex + 1) % players.size();
        return players.get(nextIndex);
    }

}
