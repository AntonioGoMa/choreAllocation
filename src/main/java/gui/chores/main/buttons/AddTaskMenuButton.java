package gui.chores.main.buttons;

import gui.chores.addTask.AddTaskMenu;
import gui.chores.main.ChoresMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTaskMenuButton implements ActionListener {

    private final ChoresMenu choresMenu;

    public AddTaskMenuButton(ChoresMenu choresMenu) {
        this.choresMenu = choresMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // this.getChoresMenu().setVisible(false);
        new AddTaskMenu(this.getChoresMenu());
    }

    public ChoresMenu getChoresMenu() {
        return choresMenu;
    }
}
