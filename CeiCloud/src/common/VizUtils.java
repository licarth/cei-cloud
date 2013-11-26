package common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class VizUtils {

	public static void barChart(int[] ints, int max) {
		int[] distr = new int[max];
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < ints.length; i++) {
			distr[ints[i]]++;
		}
		for (int i = 0; i < distr.length; i++) {
		dataset.setValue(distr[i], "D", (i)+"");
		}
		JFreeChart chart = ChartFactory.createBarChart("Items Repartition",
				"Item Size", "Number of items", dataset, PlotOrientation.VERTICAL,
				false, true, false);
		try {
			ChartUtilities.saveChartAsJPEG(new File("/home/thomas/chart.jpg"), chart, 2000, 800);
		} catch (IOException e) {
			System.err.println("Problem occurred creating chart.");
		}
	}

}
