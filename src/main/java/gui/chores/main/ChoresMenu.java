package gui.chores.main;

import gui.chores.choresToAssign.ChoresToAssignMenu;
import gui.chores.main.buttons.AddTaskMenuButton;
import gui.chores.main.buttons.ChoresToAssignButton;
import gui.chores.main.buttons.ChoresToCompleteButton;
import gui.main.MainMenu;

import javax.swing.*;
import java.awt.*;

public class ChoresMenu extends JFrame {

    private final JPanel panel = new JPanel();

    public ChoresMenu() {
        super("Chores");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);

        load();
    }

    public void load() {
        getPanel().setBackground(new Color(204, 166, 166));
        getPanel().setLayout(null);

        JLabel mainMenu = new JLabel("<html><strong><u>Chores Menu</u></strong></html>");
        mainMenu.setBounds(25, 25, 200, 25);
        mainMenu.setFont(new Font("georgia", Font.PLAIN, 25));
        getPanel().add(mainMenu);

        JButton choresToComplete = new JButton("Chores To Complete");
        choresToComplete.setBounds(25, 100, 165, 25);
        choresToComplete.setSize(200, 50);
        getPanel().add(choresToComplete);

        JButton choresToAssign = new JButton("Chores To Assign");
        choresToAssign.setBounds(25, 175, 165, 25);
        choresToAssign.setSize(200, 50);
        getPanel().add(choresToAssign);

        JButton addTask = new JButton("Add Task");
        addTask.setBounds(25, 250, 165, 25);
        addTask.setSize(200, 50);
        addTask.setBackground(Color.GREEN);
        getPanel().add(addTask);

        JButton backToMain = new JButton("Back");
        backToMain.setBounds(25, 325, 165, 25);
        backToMain.setSize(200, 50);
        getPanel().add(backToMain);

        choresToComplete.addActionListener(new ChoresToCompleteButton(this));
        choresToAssign.addActionListener(new ChoresToAssignButton(this));
        addTask.addActionListener(new AddTaskMenuButton(this));
        backToMain.addActionListener(action -> {
            this.setVisible(false);
            new MainMenu();
        });

        this.add(getPanel());
    }

    public JPanel getPanel() {
        return panel;
    }
}
