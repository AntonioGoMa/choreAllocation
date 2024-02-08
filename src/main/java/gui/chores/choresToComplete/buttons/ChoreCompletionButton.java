package gui.chores.choresToComplete.buttons;

import gui.chores.choresToComplete.ChoresToCompleteMenu;
import main.Main;
import util.tasks.Task;
import util.tasks.TaskManager;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class ChoreCompletionButton implements ActionListener {

    private final ChoresToCompleteMenu choresToCompleteMenu;
    private final Task task;

    public ChoreCompletionButton(ChoresToCompleteMenu choresToCompleteMenu, Task task) {
        this.choresToCompleteMenu = choresToCompleteMenu;
        this.task = task;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!task.getAssignedTo().equals(Main.getCurrentUser().getUsername())) {
            return;
        }

        Date date = new Date();
        TaskManager.setTaskCompleted(date, task);

        DefaultTableModel model = (DefaultTableModel) getChoresToCompleteMenu().getTasksTable().getModel();
        model.removeRow(getChoresToCompleteMenu().getTasksTable().getSelectedRow());
    }

    public Task getTask() {
        return task;
    }

    public ChoresToCompleteMenu getChoresToCompleteMenu() {
        return choresToCompleteMenu;
    }
}
