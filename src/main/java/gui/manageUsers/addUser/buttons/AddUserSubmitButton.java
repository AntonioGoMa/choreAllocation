package gui.manageUsers.addUser.buttons;

import util.users.User;
import util.users.UserManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AddUserSubmitButton implements ActionListener {

    private final Container contentPane;
    private final JTextField textField;
    private final JTable usersTable;

    public AddUserSubmitButton(Container contentPane, JTextField textField, JTable usersTable) {
        this.contentPane = contentPane;
        this.textField = textField;
        this.usersTable = usersTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = this.getTextField().getText();

        User user = Objects.requireNonNull(UserManager.addUser(username));
        getContentPane().repaint();

        ((DefaultTableModel) getUsersTable().getModel()).addRow(new Object[] { user.getId(), user.getUsername(), user.getPoints(), user.getBadge() });
        this.getContentPane().setVisible(false);

    }

    public Container getContentPane() {
        return contentPane;
    }

    public JTextField getTextField() {
        return textField;
    }

    public JTable getUsersTable() {
        return usersTable;
    }
}
