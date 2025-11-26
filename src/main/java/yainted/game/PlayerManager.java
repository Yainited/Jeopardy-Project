package yainted.game;

import java.util.ArrayList;

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
    public ArrayList<Player> getPlayers() {
        return players;
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void advanceTurn() {
        index = (index + 1) % players.size();
        currentPlayer = players.get(index);
    }
}
