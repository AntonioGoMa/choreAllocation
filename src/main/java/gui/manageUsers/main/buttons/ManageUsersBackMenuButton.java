package gui.manageUsers.main.buttons;

import gui.main.MainMenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageUsersBackMenuButton implements ActionListener {

    private final Container contentPane;

    public ManageUsersBackMenuButton(Container contentPane) {
        this.contentPane = contentPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.getContentPane().setVisible(false);
        new MainMenu();
    }

    public Container getContentPane() {
        return contentPane;
    }
}
