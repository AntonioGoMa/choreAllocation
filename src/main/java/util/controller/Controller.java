package util.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {

    // TODO: Change to use prepared statements for added layer of security
    public static ResultSet select(String query) {
        try {
            Statement statement = SQLiteManager.connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean execute(String query) {
        try {
            Statement statement = SQLiteManager.connection.createStatement();
            return statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
