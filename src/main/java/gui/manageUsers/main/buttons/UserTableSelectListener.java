package gui.manageUsers.main.buttons;

import gui.manageUsers.main.ManageUsersMenu;
import util.functions.ButtonFinder;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class UserTableSelectListener implements ListSelectionListener {

    private final ManageUsersMenu manageUsers;
    private final Container contentPane;

    public UserTableSelectListener(ManageUsersMenu manageUsers, Container contentPane) {
        this.manageUsers = manageUsers;
        this.contentPane = contentPane;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            ButtonFinder.findSpecificColorButton(this.getContentPane(), Color.GRAY).forEach(this.getContentPane()::remove);

            JButton removeUser = new JButton("Remove User");
            removeUser.setBounds(345, 175, 125, 35);
            removeUser.setBackground(Color.GRAY);
            this.getManageUsers().getPanel().add(removeUser);

            removeUser.addActionListener(new RemoveUserButton(this.getManageUsers().getTable()));

            this.getContentPane().repaint();
        }
    }

    public ManageUsersMenu getManageUsers() {
        return manageUsers;
    }

    public Container getContentPane() {
        return contentPane;
    }
}
