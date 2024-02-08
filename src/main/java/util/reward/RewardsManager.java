package util.reward;
import main.Main;
import util.controller.Controller;
import util.controller.SQLiteManager;
import util.enums.DifficultyEnum;
import util.tasks.Task;
import util.users.User;
import util.users.UserManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;



public class RewardsManager {

    public static void assignTaskDifficultyPoints(User user, Task task){ //function that assigns points based on the task difficulty
        DifficultyEnum taskDifficulty = task.getDifficulty();
        user.setPoints(user.getPoints() + taskDifficulty.getRewardedPoints());
    }

    public static void dayReward(User user, Task task) { //rewards users based on how long it took to finish a task
        Date assignD = task.getDateAssigned();
        Date complD = task.getDateCompleted();

        int daysTaken = (int) ChronoUnit.DAYS.between(assignD.toInstant(), complD.toInstant());
        int points = user.getPoints();

        System.out.println("Days taken: " + daysTaken);

        if (daysTaken <= 1) {
            points += 200;
        } else if (daysTaken <= 3) {
            points += 100;
        } else if (daysTaken == 4) {
            points += 50;
        } else if (daysTaken == 5) {
            points += 10;
        } else {
            points += 0;
        }

        user.setPoints(points);
    }
}
