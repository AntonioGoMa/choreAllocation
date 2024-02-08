package gui.manageUsers.addUser;

import gui.manageUsers.addUser.buttons.AddUserSubmitButton;

import javax.swing.*;
import java.awt.*;

public class AddUserMenu extends JFrame {

    private final JPanel panel = new JPanel();
    private final JLabel label = new JLabel("<html><u>Enter username</u></html>");
    private final JTextField textField = new JTextField(1);
    private final JButton submitButton = new JButton("Submit");

    private final JTable usersTable;

    public AddUserMenu(JTable usersTable) {
        super("Add User");

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(500, 200);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);

        this.usersTable = usersTable;

        load();
    }

    public void load() {
        getPanel().setBackground(new Color(204, 166, 166));
        getPanel().setLayout(null);

        getLabel().setBounds(200, 25, 100, 25);
        getPanel().add(getLabel());

        getTextField().setBounds(125, 75, 100, 25);
        getPanel().add(getTextField());

        getSubmitButton().setBounds(275, 75, 75, 25);
        getPanel().add(getSubmitButton());

        getSubmitButton().addActionListener(new AddUserSubmitButton(this, getTextField(), getUsersTable()));

        this.add(getPanel());
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTextField getTextField() {
        return textField;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JLabel getLabel() {
        return label;
    }

    public JTable getUsersTable() {
        return usersTable;
    }
}
