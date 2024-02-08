package gui.chores.addTask;

import gui.chores.addTask.buttons.AddTaskSubmitButton;
import gui.chores.main.ChoresMenu;
import util.enums.DifficultyEnum;

import javax.swing.*;
import java.awt.*;

public class AddTaskMenu extends JFrame {

    private final JPanel panel = new JPanel();

    private final JLabel taskLabel = new JLabel("Task Name");
    private final JTextField taskNameField = new JTextField(1);

    private final JLabel difficultyLabel = new JLabel("Difficulty");
    private final JComboBox<String> difficultyField = new JComboBox<>();

    private final JButton submitButton = new JButton("Submit");

    private final ChoresMenu choresMenu;

    public AddTaskMenu(ChoresMenu choresMenu) {
        super("Add Task");

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(500, 300);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);

        this.choresMenu = choresMenu;

        load();
    }

    public void load() {
        getPanel().setBackground(new Color(204, 166, 166));
        getPanel().setLayout(null);

        getTaskLabel().setBounds(25, 50, 100, 25);
        getPanel().add(getTaskLabel());

        getTaskName().setBounds(100, 50, 150, 25);
        getPanel().add(getTaskName());

        for (DifficultyEnum difficulty : DifficultyEnum.values()) {
            getDifficultyField().addItem(difficulty.getName());
        }

        getDifficultyLabel().setBounds(25, 150, 100, 25);
        getPanel().add(getDifficultyLabel());

        getDifficultyField().setBounds(100, 150, 150, 25);
        getPanel().add(getDifficultyField());

        getSubmitButton().setBounds(62, 200, 150, 25);
        getPanel().add(getSubmitButton());

        getSubmitButton().addActionListener(new AddTaskSubmitButton(this, this.getChoresMenu()));

        this.add(getPanel());
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTextField getTaskName() {
        return taskNameField;
    }

    public JLabel getTaskLabel() {
        return taskLabel;
    }

    public JLabel getDifficultyLabel() {
        return difficultyLabel;
    }

    public JComboBox<String> getDifficultyField() {
        return difficultyField;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public ChoresMenu getChoresMenu() {
        return choresMenu;
    }
}
