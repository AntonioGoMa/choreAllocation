package gui.main;

import gui.main.buttons.*;
import main.Main;
import util.tasks.TaskManager;
import util.users.User;
import util.users.UserManager;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class MainMenu extends JFrame {

    private final JPanel panel = new JPanel();
    private final JLabel outstandingTasksLabel = new JLabel();

    public MainMenu() {
        super("Main Menu");

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

        JLabel users = new JLabel("<html><u>Users</u></html>");
        users.setBounds(340, 25, 200, 35);
        users.setFont(new Font("georgia", Font.PLAIN, 20));
        getPanel().add(users);

        JLabel mainMenu = new JLabel("<html><strong><u>Main Menu</u></strong></html>");
        mainMenu.setBounds(25, 25, 165, 25);
        mainMenu.setFont(new Font("georgia", Font.PLAIN, 25));
        getPanel().add(mainMenu);

        JButton tasks = new JButton("⬤ Chores");
        tasks.setBounds(25, 60, 165, 20);
        tasks.setSize(250, 50);
        tasks.setHorizontalAlignment(SwingConstants.LEFT);
        getPanel().add(tasks);

        JButton statistics = new JButton("⬤ Statistics");
        statistics.setBounds(25, 120, 165, 20);
        statistics.setSize(250, 50);
        statistics.setHorizontalAlignment(SwingConstants.LEFT);
        getPanel().add(statistics);

        JButton manageUsers = new JButton("⬤ Manage Users");
        manageUsers.setBounds(25, 180, 165, 20);
        manageUsers.setSize(250, 50);
        manageUsers.setHorizontalAlignment(SwingConstants.LEFT);
        getPanel().add(manageUsers);

        JButton leaderboard = new JButton("⬤ Leaderboard");
        leaderboard.setBounds(25, 240, 165, 20);
        leaderboard.setSize(250, 50);
        leaderboard.setHorizontalAlignment(SwingConstants.LEFT);
        getPanel().add(leaderboard);

        tasks.addActionListener(new ChoresMenuButton(this));
        manageUsers.addActionListener(new ManageUsersMenuButton(this));
        statistics.addActionListener(new StatisticsMenuButton(this));
        leaderboard.addActionListener(new LeaderboardMenuButton(this));

        // load current users
        if (UserManager.users.isEmpty()) {
            JLabel noUsers = new JLabel("No users assigned.");
            noUsers.setBounds(340, 65, 200, 35);
            getPanel().add(noUsers);
        } else {
            int y = 65;
            for (int i = 0; i < UserManager.users.size(); i++) {
                JButton user = new JButton(UserManager.users.get(i).getUsername());
                 user.setBounds(340, y, 100, 35);
                user.setBackground(Color.GRAY);
                getPanel().add(user);

                if (Main.getCurrentUser() != null && user.getText().equals(Main.getCurrentUser().getUsername())) {
                    user.setBackground(Color.GREEN);

                    getOutstandingTasksLabel().setText("You have " + TaskManager.getOutstandingTasksForUser(Main.getCurrentUser().getUsername()) + " outstanding tasks!");
                    getOutstandingTasksLabel().setBounds(295, 260, 250, 20);
                    getPanel().add(getOutstandingTasksLabel());
                }

                user.addActionListener(new MainUserListListener(this, user));

                y += 40;
            }
        }

        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

        User mostPoints = UserManager.getMostPoints();
        JLabel label = new JLabel("<html><u>Information</u><br>" +
                "<br>" +
                " • Assigned Tasks: " + TaskManager.countAssignedTasks() + "<br>" +
                " • Unassigned Tasks: " + TaskManager.countUnassignedTasks() + "<br>" +
                "<br>" +
                " • Most Points: " + (mostPoints == null ? "N/A" : mostPoints.getUsername()) + " (" + (mostPoints == null ? 0 : mostPoints.getPoints()) + ")<br>" +
                " • Total Points: " + UserManager.getTotalPoints() + "</html>"
        );

        label.setBounds(25, 300, 0, 0);
        label.setSize(400, 150);
        label.setBorder(border);
        label.setFont(new Font("georgia", Font.PLAIN, 15));
        getPanel().add(label);

        this.add(getPanel());
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getOutstandingTasksLabel() {
        return outstandingTasksLabel;
    }
}
