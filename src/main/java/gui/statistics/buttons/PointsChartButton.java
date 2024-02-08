package gui.statistics.buttons;

import gui.statistics.charts.PointsChart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PointsChartButton implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        PointsChart pointsChart = new PointsChart();
        pointsChart.setVisible(true);
    }
}
