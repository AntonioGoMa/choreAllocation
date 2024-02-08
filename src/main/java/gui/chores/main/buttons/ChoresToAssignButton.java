package gui.chores.main.buttons;

import gui.chores.choresToAssign.ChoresToAssignMenu;
import gui.chores.main.ChoresMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoresToAssignButton implements ActionListener {

    private final ChoresMenu choresMenu;

    public ChoresToAssignButton(ChoresMenu choresMenu) {
        this.choresMenu = choresMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.getChoresMenu().setVisible(false);
        new ChoresToAssignMenu();
    }

    public ChoresMenu getChoresMenu() {
        return choresMenu;
    }

}
