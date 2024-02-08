package gui.statistics.buttons;

import gui.statistics.charts.TasksCompletedChart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TasksCompletedChartButton implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        TasksCompletedChart tasksCompletedChart = new TasksCompletedChart();
        tasksCompletedChart.setVisible(true);
    }
}
