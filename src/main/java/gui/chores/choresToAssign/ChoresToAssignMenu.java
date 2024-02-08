package gui.chores.choresToAssign;

import gui.chores.choresToAssign.buttons.ChoresToAssignBackButton;
import gui.chores.choresToAssign.buttons.ChoresToAssignTableSelectListener;
import util.functions.DateFormatter;
import util.tasks.TaskManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ChoresToAssignMenu extends JFrame {
    private final JPanel panel = new JPanel();
    private final JTable tasksTable = new JTable();

    public ChoresToAssignMenu() {
        super("Chores To Assign");

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

        DefaultTableModel tableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] {"ID", "Name", "Date", "Difficulty"}
        );

        getTasksTable().setModel(tableModel);
        getTasksTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TaskManager.tasksToAssign.forEach(task -> {
            ((DefaultTableModel) getTasksTable().getModel()).addRow(
                    new Object[] { task.getId(), task.getName(), DateFormatter.format(task.getDateAssigned()), task.getDifficulty() }
            );
        });

        getTasksTable().getColumnModel().getColumn(0).setPreferredWidth(10);
        getTasksTable().getColumnModel().getColumn(1).setPreferredWidth(140);
        getTasksTable().getColumnModel().getColumn(2).setPreferredWidth(40);
        getTasksTable().getColumnModel().getColumn(3).setPreferredWidth(30);
        getTasksTable().setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        JScrollPane scrollPane = new JScrollPane(tasksTable);
        scrollPane.setBounds(25, 25, 435, 300);
        getPanel().add(scrollPane);

        JButton backToMain = new JButton("Back");
        backToMain.setBounds(25, 365, 125, 50);
        getPanel().add(backToMain);

        getTasksTable().getSelectionModel().addListSelectionListener(new ChoresToAssignTableSelectListener(this));
        backToMain.addActionListener(new ChoresToAssignBackButton(this));

        this.add(getPanel());
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTable getTasksTable() {
        return tasksTable;
    }
}
