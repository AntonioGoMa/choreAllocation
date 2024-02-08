package gui.chores.choresToComplete.buttons;

import gui.chores.choresToComplete.ChoresToCompleteMenu;
import main.Main;
import util.functions.DateFormatter;
import util.tasks.Task;
import util.tasks.TaskManager;
import util.users.User;
import util.users.UserManager;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ChoreEscapeTaskButton implements ActionListener {

    private final ChoresToCompleteMenu choresToCompleteMenu;
    private final Task task;

    public ChoreEscapeTaskButton(ChoresToCompleteMenu choresToCompleteMenu, Task task) {
        this.choresToCompleteMenu = choresToCompleteMenu;
        this.task = task;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!task.getAssignedTo().equals(Main.getCurrentUser().getUsername())) {
            return;
        }

        if (Main.getCurrentUser().getPoints() < 250) {
            return;
        }

        // remove points from user
        Main.getCurrentUser().setPoints(Main.getCurrentUser().getPoints() - 250);

        // generate a random user to re-assign the task to
        User user = UserManager.users.get(new Random().nextInt(UserManager.users.size()));;

        while (user.getUsername().equals(Main.getCurrentUser().getUsername())) {
            user = UserManager.users.get(new Random().nextInt(UserManager.users.size()));
        }

        TaskManager.getTaskAssignManager().assignTaskToUser(task, user.getId());

        DefaultTableModel model = (DefaultTableModel) getChoresToCompleteMenu().getTasksTable().getModel();
        model.removeRow(getChoresToCompleteMenu().getTasksTable().getSelectedRow());
        model.addRow(new Object[]{ task.getId(), task.getName(), task.getAssignedTo(), DateFormatter.format(task.getDateAssigned()), task.getDifficulty()});
    }

    public ChoresToCompleteMenu getChoresToCompleteMenu() {
        return choresToCompleteMenu;
    }

    public Task getTask() {
        return task;
    }
}
