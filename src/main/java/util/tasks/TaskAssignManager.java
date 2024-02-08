package util.tasks;

import util.controller.Controller;
import util.users.User;
import util.users.UserManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class TaskAssignManager {

    public int countUserTimes(int taskID) {
        try {
            ResultSet resultSet = Objects.requireNonNull(Controller.select("SELECT COUNT(*) FROM taskTimes WHERE taskID = " + taskID));
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public boolean hasUserEnteredTime(int taskID, int userID) {
        try {
            ResultSet resultSet = Objects.requireNonNull(Controller.select("SELECT COUNT(*) FROM taskTimes WHERE taskID = " + taskID + " AND userID = " + userID));
            return resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void addUserTimeToTask(int taskID, int userID, int minutes) {
        try {
            int max = Objects.requireNonNull(Controller.select("SELECT max(id) FROM taskTimes")).getInt(1) + 1;
            Controller.execute("INSERT INTO taskTimes (id, taskID, userID, minutes) VALUES (" + max  + ", " + taskID + ", " + userID + ", " + minutes + ")");

            if (isTaskReadyToBeAssigned(taskID)) {
                assignTaskByLowestEstimatedTime(taskID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isTaskReadyToBeAssigned(int taskID) {
        // check if all users have added their estimated times to the unassigned task
        int count = countUserTimes(taskID);
        return count >= UserManager.users.size();
    }

    public void assignTaskByLowestEstimatedTime(int taskID) {
        // handle moving tasks to be ready to be completed section
        Task task = Objects.requireNonNull(TaskManager.findTaskById(taskID));
        TaskManager.tasksToComplete.add(task);
        TaskManager.tasksToAssign.remove(task);

        ResultSet users = Objects.requireNonNull(Controller.select("SELECT * FROM users"));

        HashMap<String, Integer> userTasks = new HashMap<>();

        try {
            while (users.next()) {
                User user = Objects.requireNonNull(UserManager.findUserByID(users.getInt("id")));
                if (!userTasks.containsKey(user.getUsername())) userTasks.put(user.getUsername(), 0);

                ResultSet tasks = Objects.requireNonNull(Controller.select("SELECT COUNT(*) FROM tasks WHERE assignedTo = '" + user.getUsername() + "'"));
                userTasks.put(user.getUsername(), tasks.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        boolean noTasksAssigned = false;

        for (String u : userTasks.keySet()) {
            if (userTasks.get(u) > 0) {
                noTasksAssigned = true;
                break;
            }
        }

        if (userTasks.isEmpty() || noTasksAssigned) { // if no tasks assigned then assign by lowest estimated time value
            try {
                ResultSet resultSet = Objects.requireNonNull(Controller.select("SELECT *, MIN(minutes) FROM taskTimes WHERE taskID = " + taskID));
                int userID = resultSet.getInt("userID");

                assignTaskToUser(task, userID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else { // find the user with the lowest number of tasks
            int lowest = 0;
            String user = "";

            for (String u : userTasks.keySet()) {
                if (userTasks.get(u) <= lowest) {
                    lowest = userTasks.get(u);
                    user = u;
                }
            }

            assignTaskToUser(task, Objects.requireNonNull(UserManager.findUserByUsername(user)).getId());
        }
    }

    public void assignTaskToUser(Task task, int userID) {
        User user = Objects.requireNonNull(UserManager.findUserByID(userID));
        Controller.execute("UPDATE tasks SET assignedTo = '" + user.getUsername() + "', allTimesGiven = '1' WHERE id = " + task.getId());
        task.setAssignedTo(user.getUsername());
    }
}
