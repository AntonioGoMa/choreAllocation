package util.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteManager {

    public static Connection connection;

    public SQLiteManager() {
        connect();
    }

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:storage.db");

            System.out.println("Connected to database: storage.db (SQLite)");

            this.createTables();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createTables() {
            boolean tasks = Controller.execute("CREATE TABLE IF NOT EXISTS tasks" +
                    "(id INTEGER NOT NULL PRIMARY KEY," +
                    "name VARCHAR(100) NOT NULL, " +
                    "assignedTo VARCHAR(100), " +
                    "assignedBy VARCHAR(100) NOT NULL, " +
                    "dateAssigned INTEGER NOT NULL, " +
                    "dateCompleted INTEGER NOT NULL, " +
                    "difficulty VARCHAR(20) NOT NULL, " +
                    "allTimesGiven BIT(1) NOT NULL)"
            );
            boolean users = Controller.execute("CREATE TABLE IF NOT EXISTS users" +
                    "(id INTEGER NOT NULL PRIMARY KEY, " +
                    "username VARCHAR(100) NOT NULL, " +
                    "points INTEGER NOT NULL, " +
                    "badge VARCHAR(20) NOT NULL)"
            );
            boolean taskTimes = Controller.execute("CREATE TABLE IF NOT EXISTS taskTimes" +
                    "(id INTEGER NOT NULL PRIMARY KEY, " +
                    "taskID INTEGER NOT NULL REFERENCES tasks, " +
                    "userID INTEGER NOT NULL REFERENCES users, " +
                    "minutes INTEGER NOT NULL)"
            );

            System.out.println("Database tables:");
            System.out.println(" - Table: tasks | Created: " + (tasks ? "true" :  "false (may have already been created)"));
            System.out.println(" - Table: users | Created: " + (users ? "true" : "false (may have already been created)"));
            System.out.println(" - Table: taskTimes | Created: " + (taskTimes ? "true" : "false (may have already been created)"));
    }

    public static boolean isConnected() {
        return connection != null;
    }

}
