package gui.main.buttons;

import gui.chores.main.ChoresMenu;
import gui.leaderboard.LeaderboardMenu;
import gui.main.MainMenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeaderboardMenuButton implements ActionListener {
    private final MainMenu mainMenu;

    public LeaderboardMenuButton(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.getMainMenu().setVisible(false);
        new LeaderboardMenu();
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }
}
