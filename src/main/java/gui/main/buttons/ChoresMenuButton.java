package gui.main.buttons;

import gui.chores.main.ChoresMenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoresMenuButton implements ActionListener {

    private final Container contentPane;

    public ChoresMenuButton(Container contentPane) {
        this.contentPane = contentPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.getContentPane().setVisible(false);
        new ChoresMenu();
    }

    public Container getContentPane() {
        return contentPane;
    }
}
