package common;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import VSCIFP.BinType;

public class VizUtils {

	public static void drawHistogramItemsRepartition(List<Integer> itemSizes, int minSize, int maxSize) {
		int[] distr = new int[maxSize];
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < itemSizes.size(); i++) {
			distr[itemSizes.get(i)-1]++;
		}
		for (int i = 0; i < distr.length; i++) {
		dataset.setValue(distr[i], "D", (i+1)+"");
		}
		JFreeChart chart = ChartFactory.createBarChart("Items Repartition",
				"Item Size", "Number of items", dataset, PlotOrientation.VERTICAL,
				false, true, false);
		try {
			ChartUtilities.saveChartAsPNG(new File("viz/itemSizes.png"), chart, 2000, 800);
		} catch (IOException e) {
			System.err.println("IProblem occurred creating chart.");
		}
	}
	
	public static void drawHistogramBinCapacities(Set<BinType> binTypes, List<Integer> binCapacities) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for (BinType t : binTypes) {
			dataset.setValue(0, "Bin Capacity", t.getCapacity()+"");//			distr[binCapacities.get(i)-1]++;
		}

		
//		dataset.setValue(0, "D", "");
		for (int i = 0; i < binCapacities.size(); i++) {
			if (dataset.getColumnKeys().contains(binCapacities.get(i)+"")){
				dataset.incrementValue(1, "Bin Capacity", binCapacities.get(i)+"");//			distr[binCapacities.get(i)-1]++;
			} else {
				dataset.setValue(1, "Bin Capacity", binCapacities.get(i)+"");//			distr[binCapacities.get(i)-1]++;
			}
		}
		JFreeChart chart = ChartFactory.createBarChart("Items Repartition",
				"Item Size", "Number of items", dataset, PlotOrientation.VERTICAL,
				false, true, false);
		try {
			ChartUtilities.saveChartAsPNG(new File("viz/binSizes.png"), chart, 2000, 800);
		} catch (IOException e) {
			System.err.println("IProblem occurred creating chart.");
		}
	}

}
