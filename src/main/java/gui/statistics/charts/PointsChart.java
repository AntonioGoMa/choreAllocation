package gui.statistics.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import util.users.User;
import util.users.UserManager;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;


public class PointsChart extends JFrame {

    public PointsChart() {
        setTitle("Points Chart");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 500);

        load();
    }

    public void load() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        for (User user : UserManager.users) {
            dataset.setValue(user.getUsername(), user.getPoints());
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "User Points",
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