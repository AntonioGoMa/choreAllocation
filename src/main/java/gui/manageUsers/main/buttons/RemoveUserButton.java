package gui.manageUsers.main.buttons;

import util.users.UserManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveUserButton implements ActionListener {

    private final JTable table;

    public RemoveUserButton(JTable table) {
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UserManager.removeUser((String) this.getTable().getValueAt(getTable().getSelectedRow(), 1));
        DefaultTableModel model = (DefaultTableModel) getTable().getModel();
        model.removeRow(getTable().getSelectedRow());
    }

    public JTable getTable() {
        return table;
    }
}
