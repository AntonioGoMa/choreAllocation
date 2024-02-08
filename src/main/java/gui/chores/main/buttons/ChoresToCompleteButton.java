package gui.chores.main.buttons;

import gui.chores.choresToComplete.ChoresToCompleteMenu;
import gui.chores.main.ChoresMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoresToCompleteButton implements ActionListener {

    private final ChoresMenu choresMenu;

    public ChoresToCompleteButton(ChoresMenu choresMenu) {
        this.choresMenu = choresMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.getChoresMenu().setVisible(false);
        new ChoresToCompleteMenu();
    }

    public ChoresMenu getChoresMenu() {
        return choresMenu;
    }
}
