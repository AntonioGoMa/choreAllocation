package gui.manageUsers.main.buttons;

import gui.manageUsers.addUser.AddUserMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserMenuButton implements ActionListener {

    private final Container contentPane;
    private final JTable table;

    public AddUserMenuButton(Container contentPane, JTable table) {
        this.contentPane = contentPane;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // this.getContentPane().setVisible(false);
        new AddUserMenu(getTable());
    }

    public Container getContentPane() {
        return contentPane;
    }

    public JTable getTable() {
        return table;
    }
}
