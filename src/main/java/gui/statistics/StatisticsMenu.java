package gui.statistics;

import gui.manageUsers.main.buttons.ManageUsersBackMenuButton;
import gui.statistics.buttons.PointsChartButton;
import gui.statistics.buttons.TasksCompletedChartButton;
import gui.statistics.charts.PointsChart;
import gui.statistics.charts.TasksCompletedChart;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatisticsMenu extends JFrame {

    private final JPanel panel = new JPanel();

    public StatisticsMenu() {
        super("Stats");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setResizable(false);

        setVisible(true);
        setLocationRelativeTo(null);

        load();
    }

    public void load() {
        getPanel().setBackground(new Color(204, 166, 166));;
        getPanel().setLayout(null);

        JLabel mainMenu = new JLabel("<html><strong><u>Statistics Charts</u></strong></html>");
        mainMenu.setBounds(25, 25, 250, 25);
        mainMenu.setFont(new Font("georgia", Font.PLAIN, 25));
        getPanel().add(mainMenu);

        JButton userPointsChart = new JButton("User Points Chart");
        userPointsChart.setBounds(25, 60, 200, 50);
        getPanel().add(userPointsChart);

        JButton tasksCompletedChart = new JButton("Tasks Completed Chart");
        tasksCompletedChart.setBounds(25, 120, 200, 50);
        getPanel().add(tasksCompletedChart);

        JButton backToMain = new JButton("Back");
        backToMain.setBounds(25, 365, 150, 50);
        getPanel().add(backToMain);

        // setup seperate classes to handle different button clicks
        backToMain.addActionListener(new ManageUsersBackMenuButton(this));
        userPointsChart.addActionListener(new PointsChartButton());
        tasksCompletedChart.addActionListener(new TasksCompletedChartButton());

        this.add(getPanel());
    }

    private JPanel getPanel() {
        return this.panel;
    }
}


