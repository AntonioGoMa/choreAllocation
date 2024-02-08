package gui.main.buttons;

import gui.chores.main.ChoresMenu;
import gui.main.MainMenu;
import gui.statistics.StatisticsMenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatisticsMenuButton implements ActionListener {
    private final MainMenu mainMenu;

    public StatisticsMenuButton(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.getMainMenu().setVisible(false);
        new StatisticsMenu();
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }
}
