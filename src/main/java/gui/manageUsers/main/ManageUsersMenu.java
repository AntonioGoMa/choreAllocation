package gui.manageUsers.main;

import gui.manageUsers.main.buttons.AddUserMenuButton;
import gui.manageUsers.main.buttons.ManageUsersBackMenuButton;
import gui.manageUsers.main.buttons.UserTableSelectListener;
import util.users.UserManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ManageUsersMenu extends JFrame {

    private final JPanel panel = new JPanel();
    private final JTable table = new JTable();

    public ManageUsersMenu() {
        super("Manage Users");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);

        load();
    }

    public void load() {
        getPanel().setBackground(new Color(204, 166, 166));
        getPanel().setLayout(null);

        getTable().setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {"ID", "Name", "Points", "Badge"})
        );

        getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        UserManager.users.forEach(user -> ((DefaultTableModel) getTable().getModel()).addRow(
                new Object[] { user.getId(), user.getUsername(), user.getPoints(), user.getBadge() }
        ));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(25, 25, 300, 300);
        getPanel().add(scrollPane);

        JButton backToMain = new JButton("Back");
        backToMain.setBounds(25, 365, 150, 50);
        getPanel().add(backToMain);

        JButton addUser = new JButton("Add User");
        addUser.setBounds(215, 365, 150, 50);
        addUser.setBackground(Color.GREEN);
        getPanel().add(addUser);

        // setup seperate classes to handle different button clicks
        getTable().getSelectionModel().addListSelectionListener(new UserTableSelectListener(this, this.getContentPane()));
        backToMain.addActionListener(new ManageUsersBackMenuButton(this));
        addUser.addActionListener(new AddUserMenuButton(this, getTable()));

        this.add(getPanel());
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTable getTable() {
        return table;
    }
}
