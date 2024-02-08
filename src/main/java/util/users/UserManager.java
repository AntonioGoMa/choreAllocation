package util.users;

import util.controller.Controller;
import util.controller.SQLiteManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class UserManager {

    public static final ArrayList<User> users = new ArrayList<>();

    public UserManager() {
        loadUsers();
    }

    public static void main(String[] args) {
        // load sql because some of these functions require a sql connection
        new SQLiteManager();

        // TEST 1

        System.out.println("[TEST 1] Expected: Loads all the users into the users array with all the correct values\n");
        loadUsers();

        if (!users.isEmpty()) System.out.println("[RESULT] Users successfully loaded");
        else System.out.println("[RESULT] Users were not loaded\n\n");
        System.out.println("#### TEST 1 COMPLETE ####\n");

        // TEST 2

        System.out.println("[TEST 2] Expected: User added with name Rhys with default values\n");
        User a = addUser("Rhys");

        if (users.contains(a)) System.out.println("[RESULT] User added successfully");
        else System.out.println("[RESULT] User not added successfully\n\n");
        System.out.println("#### TEST 2 COMPLETE ####\n");
    }

    public static void loadUsers() {
        ResultSet resultSet = Objects.requireNonNull(Controller.select("SELECT * FROM users ORDER BY points DESC"));

            try {
                while (resultSet.next()) {
                    User user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("username"),
                            resultSet.getInt("points"),
                            resultSet.getString("badge")
                    );

                    users.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    public static int getUsersCount() {
        return users.size();
    }

    public static int getTotalPoints() {
        int total = 0;

        for (User user : users) {
            total += user.getPoints();
        }

        return total;
    }

    public static User getMostPoints() {
        int total = 0;
        User mostPoints = null;

        for (User user : users) {
            if (user.getPoints() >= total) {
                total = user.getPoints();
                mostPoints = user;
            }
        }

        return mostPoints;
    }

    public static User addUser(String username) {
        try {
            int max = Objects.requireNonNull(Controller.select("SELECT max(id) FROM users")).getInt(1) + 1;
            Controller.execute("INSERT INTO users (id, username, points, badge) VALUES (" + max + ", '" + username + "', 0, 'N/A')");

            User user = new User(max, username, 0, "N/A");
            users.add(user);

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) return user;
        }

        return null;
    }

    public static User findUserByID(int userID) {
        for (User user : users) {
            if (user.getId() == userID) return user;
        }

        return null;
    }

    public static void removeUser(String username) {
        User user = findUserByUsername(username);

        if (user != null) {
            Controller.execute("DELETE FROM users WHERE username = '" + username + "'");
            users.remove(user);
        }

    }
}
