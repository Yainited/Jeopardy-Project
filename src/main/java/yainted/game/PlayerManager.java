package yainted.game;

import java.util.List;

public class PlayerManager {

    private List<Player> players;
    private int index = 0;
    private Player currentPlayer;

    public PlayerManager(List<Player> players) {
        this.players = players;
        currentPlayer = players.get(0);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void advanceTurn() {
        index = (index + 1) % players.size();
        currentPlayer = players.get(index);
    }
}
