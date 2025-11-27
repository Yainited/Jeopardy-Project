package yainted.gui;

import yainted.events.EnterPlayerNameGameEvent;
import yainted.observer.EventManager;

public class playerGUI {
    private javax.swing.JTextField playerNameField;
    private javax.swing.JTextField playerScoreLabel;

    public playerGUI( javax.swing.JTextField nameField, javax.swing.JTextField scoreLabel) {
        this.playerNameField = nameField;
        this.playerScoreLabel = scoreLabel;
    }

    public String getPlayerName() {
        return playerNameField.getText();
    }
    public void setPlayerName(String name) {
        playerNameField.setText(name);
    }
    public void setPlayerScore(int score) {
        playerScoreLabel.setText(String.valueOf(score));
    }
    public void setVisible(boolean visible) {
        playerNameField.setVisible(visible);
        playerScoreLabel.setVisible(visible);
    }
    public void setTextColor(java.awt.Color color) {
        playerNameField.setForeground(color);
    }
}
