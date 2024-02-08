package gui.chores.choresToAssign.buttons;

import gui.chores.choresToAssign.ChoresToAssignMenu;
import gui.chores.main.ChoresMenu;
import gui.main.MainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoresToAssignBackButton implements ActionListener {

    private final ChoresToAssignMenu choresToAssignMenu;

    public ChoresToAssignBackButton(ChoresToAssignMenu choresToAssignMenu) {
        this.choresToAssignMenu = choresToAssignMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.getChoresMenu().setVisible(false);
        new ChoresMenu();
    }

    public ChoresToAssignMenu getChoresMenu() {
        return choresToAssignMenu;
    }

}
