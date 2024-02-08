package gui.chores.addUserTimeToTask.buttons;

import gui.chores.addUserTimeToTask.AddTimeMenu;
import gui.chores.choresToAssign.ChoresToAssignMenu;
import main.Main;
import util.functions.ButtonFinder;
import util.tasks.Task;
import util.tasks.TaskManager;
import util.users.UserManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTimeSubmitButton implements ActionListener {

    private final ChoresToAssignMenu choresToAssignMenu;
    private final AddTimeMenu addTimeMenu;
    private final Task task;
    private final JTextField estimatedTimeInMinutes;

    public AddTimeSubmitButton(ChoresToAssignMenu choresToAssignMenu, AddTimeMenu addTimeMenu, Task task, JTextField estimatedTimeInMinutes) {
        this.choresToAssignMenu = choresToAssignMenu;
        this.addTimeMenu = addTimeMenu;
        this.task = task;
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TaskManager.getTaskAssignManager().addUserTimeToTask(getTask().getId(), Main.getCurrentUser().getId(), Integer.parseInt(getEstimatedTimeInMinutes().getText()));
        getAddTimeMenu().setVisible(false);

        ButtonFinder.findSpecificColorButton(getChoresToAssignMenu().getPanel(), Color.ORANGE).forEach(button -> getChoresToAssignMenu().getPanel().remove(button));
        getChoresToAssignMenu().getPanel().repaint();

        // clean up the table in the tasks to assign menu table
        if (TaskManager.getTaskAssignManager().isTaskReadyToBeAssigned(getTask().getId())) {
            DefaultTableModel model = (DefaultTableModel) getChoresToAssignMenu().getTasksTable().getModel();
            model.removeRow(getChoresToAssignMenu().getTasksTable().getSelectedRow());
        }
    }

    public AddTimeMenu getAddTimeMenu() {
        return addTimeMenu;
    }

    public Task getTask() {
        return task;
    }

    public JTextField getEstimatedTimeInMinutes() {
        return estimatedTimeInMinutes;
    }

    public ChoresToAssignMenu getChoresToAssignMenu() {
        return choresToAssignMenu;
    }
}
