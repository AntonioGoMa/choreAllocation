package util.tasks;

import main.Main;
import util.controller.SQLiteManager;
import util.reward.RewardsManager;
import util.enums.DifficultyEnum;
import util.controller.Controller;
import util.users.User;
import util.users.UserManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class TaskManager {

    private static TaskAssignManager taskAssignManager = new TaskAssignManager();

    public static ArrayList<Task> tasksToComplete = new ArrayList<>();
    public static ArrayList<Task> tasksToAssign = new ArrayList<>();
    public static ArrayList<Task> tasksCompleted = new ArrayList<>();

    public TaskManager() {
        loadTasks();
    }

    public static void main(String[] args){
        // Will load the SQL as functions are in need of requirement from a SQL Connection
        new SQLiteManager();

        // Test 1

        System.out.println("[TESTING1] Should be able to load all task's and with correct values\n");
        loadTasks();

        if(tasksToAssign.isEmpty())System.out.println("[Result] Task's will be assigned accordingly");
        else System.out.println("Intended Result: No task are assigned as no users are to be assigned tasks");
        System.out.println(("## Test 1 should be completed \n"));

        // Test 2

        System.out.println("[Testing 2] Intended: Task will have been added for a user with easy difficulty along with default values \n");
        Task a = addTask("Cleaning", DifficultyEnum.EASY);

        if(tasksToComplete.contains(a)) System.out.println("[RESULT] Task assigned to user accepted");
        else System.out.println("[Result] Task was unsucessful in being added to users\n\n");
        System.out.println("### Test 2 should now be working as intended use \n");
    }

    public static void loadTasks() {
        ResultSet resultSet = Objects.requireNonNull(Controller.select("SELECT * FROM tasks"));

        try {
            while (resultSet.next()) {
                boolean allTimesGiven = resultSet.getBoolean("allTimesGiven");
                boolean completed = resultSet.getLong("dateCompleted") != 0;

                Task task = new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("assignedTo"),
                        resultSet.getString("assignedBy"),
                        new Date(resultSet.getLong("dateAssigned")),
                        new Date(resultSet.getLong("dateCompleted")),
                        DifficultyEnum.valueOf(resultSet.getString("difficulty")),
                        resultSet.getBoolean("allTimesGiven")
                );


                if (completed) tasksCompleted.add(task);
                else {
                    if (allTimesGiven) tasksToComplete.add(task);
                    else tasksToAssign.add(task);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Task addTask(String taskName, DifficultyEnum difficulty) {
        try {
            int max = Objects.requireNonNull(Controller.select("SELECT max(id) FROM tasks")).getInt(1) + 1;
            User assignedBy = Main.getCurrentUser();
            long dateAssigned = new Date().getTime();

            Controller.execute("INSERT INTO " +
                    "tasks (id, name, assignedTo, assignedBy, dateAssigned, dateCompleted, difficulty, allTimesGiven) " +
                    "VALUES (" + max + ", '" + taskName + "', '', '" + assignedBy.getUsername() + "', '" + dateAssigned + "', '" + 0 + "', '" + difficulty.getName() + "', 0)"
            );

            Task task = new Task(
                    max,
                    taskName,
                    "",
                    assignedBy.getUsername(),
                    new Date(dateAssigned),
                    new Date(0),
                    difficulty,
                    false
            );

            tasksToAssign.add(task);
            return task;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static int getOutstandingTasksForUser(String username) {
        try {
            int count = Objects.requireNonNull(Controller.select("SELECT COUNT(*) FROM tasks WHERE assignedTo = '" + username + "' AND dateCompleted = 0")).getInt(1);
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static int countAssignedTasks() {
        try {
            return Objects.requireNonNull(Controller.select("SELECT COUNT(*) FROM tasks WHERE allTimesGiven = 1 AND dateCompleted = 0")).getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static int countUnassignedTasks() {
        try {
            return Objects.requireNonNull(Controller.select("SELECT COUNT(*) FROM tasks WHERE allTimesGiven = 0 AND dateCompleted = 0")).getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static void setTaskCompleted(Date date, Task task) {
        Controller.execute("UPDATE tasks SET dateCompleted = " + date.getTime() + " WHERE id = " + task.getId());
        task.setDateCompleted(date);
        tasksToComplete.remove(task);
        tasksCompleted.add(task);

        // handle points rewarding
        User user = Objects.requireNonNull(UserManager.findUserByUsername(task.getAssignedTo()));
        RewardsManager.dayReward(user, task);
        RewardsManager.assignTaskDifficultyPoints(user, task);

        Controller.execute("UPDATE users SET points = " + user.getPoints() + " WHERE username = '" + user.getUsername() + "'");
    }

    public static Task findTaskById(int id) {
        // check assigned tasks
        for (Task task : tasksToComplete) {
            if (task.getId() == id) {
                return task;
            }
        }

        // check unassigned tasks
        for (Task task : tasksToAssign) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    // TODO: will do later cos im lazy
    public static void removeTask() {

    }

    public static TaskAssignManager getTaskAssignManager() {
        return taskAssignManager;
    }
}
