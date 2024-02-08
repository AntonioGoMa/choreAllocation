package gui.statistics.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import util.tasks.Task;
import util.tasks.TaskManager;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.HashMap;

public class TasksCompletedChart extends JFrame {

    public TasksCompletedChart() {
        setTitle("Tasks Completed Chart");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 500);

        load();
    }

    public void load() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        HashMap<String, Integer> map = new HashMap<>();
        for (Task task : TaskManager.tasksCompleted) {
            if (map.containsKey(task.getAssignedTo())) {
                map.put(task.getAssignedTo(), map.get(task.getAssignedTo()) + 1);
            } else {
                map.put(task.getAssignedTo(), 1);
            }
        }

        for (String username : map.keySet()) {
            dataset.setValue(username, map.get(username));
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Tasks Completed Chart",
                dataset,
                true,
                true,
                false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSimpleLabels(false);

        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator("{0}: {1} ({2})", NumberFormat.getInstance(), NumberFormat.getPercentInstance());
        plot.setLabelGenerator(gen);

        ChartPanel chartPanel = new ChartPanel(chart);
        getContentPane().add(chartPanel, BorderLayout.CENTER);
    }
}
