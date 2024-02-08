package gui.chores.addTask.buttons;

import gui.chores.addTask.AddTaskMenu;
import gui.chores.main.ChoresMenu;
import util.enums.DifficultyEnum;
import util.tasks.Task;
import util.tasks.TaskManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AddTaskSubmitButton implements ActionListener {

    private final AddTaskMenu addTaskMenu;
    private final ChoresMenu choresMenu;

    public AddTaskSubmitButton(AddTaskMenu addTaskMenu, ChoresMenu choresMenu) {
        this.addTaskMenu = addTaskMenu;
        this.choresMenu = choresMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String taskName = this.getAddTaskMenu().getTaskName().getText();
        // User assignedTo = Objects.requireNonNull(UserManager.findUser((String) this.getAddTaskMenu().getTaskAssignedToField().getSelectedItem()));
        DifficultyEnum difficulty = DifficultyEnum.valueOf((String) this.getAddTaskMenu().getDifficultyField().getSelectedItem());

        Task task = Objects.requireNonNull(TaskManager.addTask(taskName, difficulty));

        /*((DefaultTableModel) this.getChoresMenu().getTasksTable().getModel()).addRow(
                new Object[] {
                        task.getId(), task.getName(), task.getAssignedTo(), DateFormatter.format(task.getDateAssigned()), task.getDifficulty()
                });
         */

        this.getAddTaskMenu().setVisible(false);
    }

    public AddTaskMenu getAddTaskMenu() {
        return addTaskMenu;
    }

    public ChoresMenu getChoresMenu() {
        return choresMenu;
    }
}
