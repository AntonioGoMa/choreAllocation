package gui.chores.choresToComplete.buttons;

import gui.chores.choresToComplete.ChoresToCompleteMenu;
import main.Main;
import util.functions.ButtonFinder;
import util.tasks.Task;
import util.tasks.TaskManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Objects;

public class ChoresToCompleteTableSelectListener implements ListSelectionListener {

    private final ChoresToCompleteMenu choresMenu;

    public ChoresToCompleteTableSelectListener(ChoresToCompleteMenu choresMenu) {
        this.choresMenu = choresMenu;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if (this.getChoresMenu().getTasksTable().getSelectedRow() == -1) return;

            ButtonFinder.findSpecificColorButton(this.getChoresMenu().getContentPane(), Color.ORANGE).forEach(oldCompleteButton -> {
                this.getChoresMenu().getPanel().remove(oldCompleteButton);
                this.getChoresMenu().getPanel().revalidate();
                this.getChoresMenu().getPanel().repaint();

                System.out.println("removed");
            });

            int taskID = (int) this.getChoresMenu().getTasksTable().getValueAt(this.getChoresMenu().getTasksTable().getSelectedRow(), 0);

            Task task = Objects.requireNonNull(TaskManager.findTaskById(taskID));

            // do not generate buttons if selected task is not assigned to the user
            if (!Main.getCurrentUser().getUsername().equals(task.getAssignedTo())) {
                return;
            }

            JButton completeTask = new JButton("Complete");
            completeTask.setBounds(175, 365, 125, 50);
            completeTask.setBackground(Color.ORANGE);
            this.getChoresMenu().getPanel().add(completeTask);

            JButton escapeTask = new JButton("Escape (250p)");
            escapeTask.setBounds(325, 365, 125, 50);
            escapeTask.setBackground(Color.PINK);
            this.getChoresMenu().getPanel().add(escapeTask);

            completeTask.addActionListener(new ChoreCompletionButton(getChoresMenu(), task));
            escapeTask.addActionListener(new ChoreEscapeTaskButton(getChoresMenu(), task));

            this.getChoresMenu().getPanel().repaint();
        }
    }

    public ChoresToCompleteMenu getChoresMenu() {
        return choresMenu;
    }
}
