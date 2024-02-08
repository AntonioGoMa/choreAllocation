package gui.chores.choresToComplete.buttons;

import gui.chores.choresToComplete.ChoresToCompleteMenu;
import gui.chores.main.ChoresMenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoresToCompleteBackButton implements ActionListener {

    private final ChoresToCompleteMenu choresMenu;

    public ChoresToCompleteBackButton(ChoresToCompleteMenu choresMenu) {
        this.choresMenu = choresMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.getChoresMenu().setVisible(false);
        new ChoresMenu();
    }

    public Container getChoresMenu() {
        return choresMenu;
    }

}
