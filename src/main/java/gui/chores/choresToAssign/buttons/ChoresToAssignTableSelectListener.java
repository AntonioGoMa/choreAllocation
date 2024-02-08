package gui.chores.choresToAssign.buttons;

import gui.chores.addUserTimeToTask.AddTimeMenu;
import gui.chores.choresToAssign.ChoresToAssignMenu;
import main.Main;
import util.functions.ButtonFinder;
import util.tasks.Task;
import util.tasks.TaskManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Objects;

public class ChoresToAssignTableSelectListener implements ListSelectionListener {

    private final ChoresToAssignMenu choresToAssignMenu;

    public ChoresToAssignTableSelectListener(ChoresToAssignMenu choresToAssignMenu) {
        this.choresToAssignMenu = choresToAssignMenu;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if (this.getChoresToAssignMenu().getTasksTable().getSelectedRow() == -1) return;

            int taskID = (int) this.getChoresToAssignMenu().getTasksTable().getValueAt(this.getChoresToAssignMenu().getTasksTable().getSelectedRow(), 0);

            Task task = Objects.requireNonNull(TaskManager.findTaskById(taskID));

            // remove last buttons from previously clicked tasks
            ButtonFinder.findSpecificColorButton(this.getChoresToAssignMenu(), Color.ORANGE).forEach(button -> this.getChoresToAssignMenu().getPanel().remove(button));
            ButtonFinder.findSpecificColorButton(this.getChoresToAssignMenu(), Color.RED).forEach(button -> this.getChoresToAssignMenu().getPanel().remove(button));
            this.getChoresToAssignMenu().getPanel().repaint();

            // load new button for the task that was clicked
            JButton viewDetails = new JButton();

            if (TaskManager.getTaskAssignManager().hasUserEnteredTime(task.getId(), Main.getCurrentUser().getId())) {
                viewDetails.setText("Already entered time");
                viewDetails.setBackground(Color.RED);
            } else {
                viewDetails.setText("Enter Time");
                viewDetails.setBackground(Color.ORANGE);
                viewDetails.addActionListener(listener -> {
                    new AddTimeMenu(getChoresToAssignMenu(), task);
                });
            }

            viewDetails.setBounds(175, 365, 200, 50);
            this.getChoresToAssignMenu().getPanel().add(viewDetails);
        }
    }

    public ChoresToAssignMenu getChoresToAssignMenu() {
        return choresToAssignMenu;
    }
}
