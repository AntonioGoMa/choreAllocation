package gui.main.buttons;

import gui.manageUsers.main.ManageUsersMenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageUsersMenuButton implements ActionListener {

    private final Container contentPane;

    public ManageUsersMenuButton(Container contentPane) {
        this.contentPane = contentPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.getContentPane().setVisible(false);
        new ManageUsersMenu();
    }

    public Container getContentPane() {
        return contentPane;
    }
}
