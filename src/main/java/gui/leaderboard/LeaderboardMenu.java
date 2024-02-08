package gui.leaderboard;

import gui.manageUsers.main.buttons.AddUserMenuButton;
import gui.manageUsers.main.buttons.ManageUsersBackMenuButton;
import gui.manageUsers.main.buttons.UserTableSelectListener;
import util.users.User;
import util.users.UserManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Collections;
import java.util.Comparator;

public class LeaderboardMenu extends JFrame {
    private final JPanel panel = new JPanel();
    private final JTable table = new JTable();

    public LeaderboardMenu() {
        super("Leaderboard");

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
                new String[] {"ID", "Name", "Points"})
        );

        getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // sort users by points
        UserManager.users.sort((o1, o2) -> o2.getPoints() - o1.getPoints());

        UserManager.users.forEach(user -> ((DefaultTableModel) getTable().getModel()).addRow(
                new Object[] { user.getId(), user.getUsername(), user.getPoints() }
        ));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(25, 25, 300, 300);
        getPanel().add(scrollPane);

        JButton backToMain = new JButton("Back");
        backToMain.setBounds(25, 365, 150, 50);
        getPanel().add(backToMain);

        // setup seperate classes to handle different button clicks
        backToMain.addActionListener(new ManageUsersBackMenuButton(this));

        this.add(getPanel());
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTable getTable() {
        return table;
    }
}
