
package main;

import gui.main.MainMenu;
import util.tasks.TaskManager;
import util.users.User;
import util.users.UserManager;
import util.controller.SQLiteManager;

public class Main {

    private static User CURRENT_USER;

    public static void main(String[] args) {
        new SQLiteManager();
        new UserManager();
        new TaskManager();

        new MainMenu();
    }

    public static User getCurrentUser() {
        return CURRENT_USER;
    }

    public static void setCurrentUser(User user) {
        CURRENT_USER = user;
    }
}