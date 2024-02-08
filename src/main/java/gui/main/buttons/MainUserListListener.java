package gui.main.buttons;

import gui.main.MainMenu;
import main.Main;
import util.functions.ButtonFinder;
import util.tasks.TaskManager;
import util.users.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUserListListener implements ActionListener {

    private final MainMenu mainMenu;
    private final JButton userClicked;

    public MainUserListListener(MainMenu mainMenu, JButton userClicked) {
        this.mainMenu = mainMenu;
        this.userClicked = userClicked;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonFinder.findSpecificColorButton(this.getMainMenu().getContentPane(), Color.GREEN).forEach(button -> {
            button.setBackground(Color.GRAY);
        });

        this.getUserClicked().setBackground(Color.GREEN);
        Main.setCurrentUser(UserManager.findUserByUsername(this.getUserClicked().getText()));

        getMainMenu().getOutstandingTasksLabel().setText("You have " + TaskManager.getOutstandingTasksForUser(Main.getCurrentUser().getUsername()) + " outstanding tasks!");
        getMainMenu().getOutstandingTasksLabel().setBounds(295, 260, 250, 20);
        getMainMenu().getPanel().add(getMainMenu().getOutstandingTasksLabel());

        getMainMenu().repaint();
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public JButton getUserClicked() {
        return userClicked;
    }
}
