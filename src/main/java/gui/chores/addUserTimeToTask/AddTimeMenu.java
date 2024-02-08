package gui.chores.addUserTimeToTask;

import gui.chores.addUserTimeToTask.buttons.AddTimeSubmitButton;
import gui.chores.choresToAssign.ChoresToAssignMenu;
import util.tasks.Task;

import javax.swing.*;
import java.awt.*;

public class AddTimeMenu extends JFrame {

    private final ChoresToAssignMenu choresToAssignMenu;
    private final Task task;

    private final JPanel panel = new JPanel();

    private final JLabel label = new JLabel("<html><u>Your estimated time for this task (minutes)</u></html>");
    private final JTextField textField = new JTextField(1);
    private final JButton submitButton = new JButton("Submit");

    public AddTimeMenu(ChoresToAssignMenu choresToAssignMenu, Task task) {
        super("Add Time");

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(500, 200);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);

        this.choresToAssignMenu = choresToAssignMenu;
        this.task = task;

        load();
    }

    public void load() {
        getPanel().setBackground(new Color(204, 166, 166));
        getPanel().setLayout(null);

        getLabel().setBounds(150, 25, 200, 25);
        getPanel().add(getLabel());

        getTextField().setBounds(125, 75, 100, 25);
        getPanel().add(getTextField());

        getSubmitButton().setBounds(275, 75, 75, 25);
        getPanel().add(getSubmitButton());

        getSubmitButton().addActionListener(new AddTimeSubmitButton(getChoresToAssignMenu(), this, getTask(), getTextField()));

        this.add(getPanel());
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getLabel() {
        return label;
    }

    public JTextField getTextField() {
        return textField;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public Task getTask() {
        return task;
    }

    public ChoresToAssignMenu getChoresToAssignMenu() {
        return choresToAssignMenu;
    }
}
