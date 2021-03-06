package task_reg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

public class Diagrammo extends ApplicationFrame {
	Map<String, Double> statussPercent = new HashMap<String, Double>();
	/*double doneCountPercent;
	double failedCountPercent;
	double cancelledCountPercent;
	double bossdiditCountPercent;*/

	public Diagrammo(String title, Map<String, ArrayList<String>> allYearTaskList) {
		super(title);
		for (String s:Main.statussList) {
			this.statussPercent.put(s, getTaskCountPercent(allYearTaskList, s));
		}
		/*this.doneCountPercent = getTaskCountPercent(allYearTaskList, "DONE");
		this.failedCountPercent = getTaskCountPercent(allYearTaskList, "FAILED");
		this.cancelledCountPercent = getTaskCountPercent(allYearTaskList, "CANCELLED");
		this.bossdiditCountPercent = getTaskCountPercent(allYearTaskList, "BOSSDIDIT");*/
		setContentPane(createDemoPanel());
	}

	public PieDataset createDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		for (String s:Main.statussList) {
			dataset.setValue(s+" (%)",  Methods.getRoundedDouble(this.statussPercent.get(s)));
		}
		/*dataset.setValue("Done (%)", Methods.getRoundedDouble(this.doneCountPercent));
		dataset.setValue("Failed (%)", Methods.getRoundedDouble(this.failedCountPercent));
		dataset.setValue("Cancelled (%)", Methods.getRoundedDouble(this.cancelledCountPercent));
		dataset.setValue("Bossdidit (%)", Methods.getRoundedDouble(this.bossdiditCountPercent));*/
		return dataset;
	}

	public JFreeChart createChart(PieDataset dataset) {
		JFreeChart chart = ChartFactory.createPieChart("This years tasks", // chart title
				dataset, // data
				true, // include legend
				true, false);

		return chart;
	}

	public JPanel createDemoPanel() {
		JFreeChart chart = createChart(createDataset());
		return new ChartPanel(chart);
	}

	// atgriež visa gada uzdevumu skaitu
	public double getTotalTaskCount(Map<String, ArrayList<String>> allYearTaskList) {
		double count = 0;
		for (String d : Main.filenames) {
			for (String r : allYearTaskList.get(d)) {
				if (r.length() > 0) {
					count++;
				}
			}
		}
		return count;
	}

	// atgriež konkrētā uzdevuma procentuālo daudzumu gadā
	public double getTaskCountPercent(Map<String, ArrayList<String>> allYearTaskList, String task) {
		double count = 0;
		for (String d : Main.filenames) {
			for (String r : allYearTaskList.get(d)) {
				if (r.length() > 0 && r.split(Main.delimiter)[2].equals(task)) {
					count++;
				}
			}
		}
		count = getPercent(allYearTaskList, count);
		return count;
	}

	// pārveido uz procentiem
	public double getPercent(Map<String, ArrayList<String>> allYearTaskList, double taskCount) {
		double totalTaskCount = getTotalTaskCount(allYearTaskList);
		double taskPercent = (taskCount / totalTaskCount) * 100;
		return taskPercent;
	}
}
