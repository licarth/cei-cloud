package common;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import VSCIFP.BinType;

public class VizUtils {

	public static void drawHistogramItemsRepartition(List<Integer> itemSizes, int minSize, int maxSize, String ... prefSuff) {
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
		applyStyle(chart);
		try {
			ChartUtilities.saveChartAsPNG(new File(getFileName("itemSizes",prefSuff)), chart, 2000, 800);
		} catch (IOException e) {
			System.err.println("Problem occurred creating chart.");
		}
	}
	
	private static String getFileName(String coreName, String[] prefSuff) {
		return "viz/"+((prefSuff.length>0)?prefSuff[0]:"")+coreName+((prefSuff.length>1)?prefSuff[1]:"")+".png";
	}

	public static void drawHistogramBinCapacities(Set<BinType> binTypes, List<Integer> binCapacities, String ... prefSuff) {
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
		JFreeChart chart = ChartFactory.createBarChart("Bin Capacities",
				"BinType capacity", "Number of bins of this type.", dataset, PlotOrientation.VERTICAL,
				false, true, false);
		applyStyle(chart);
		try {
			ChartUtilities.saveChartAsPNG(new File(getFileName("binCapacities",prefSuff)), chart, 2000, 800);
		} catch (IOException e) {
			System.err.println("IProblem occurred creating chart.");
		}
	}
	
	private static void applyStyle(JFreeChart chart) {
		CategoryAxis domainAxis = chart.getCategoryPlot().getDomainAxis(); 
		domainAxis.setLabelFont(domainAxis.getLabelFont().deriveFont(30F)); 
		domainAxis.setTickLabelFont(domainAxis.getTickLabelFont().deriveFont(25F)); 
		ValueAxis rangeAxis = chart.getCategoryPlot().getRangeAxis(); 
		rangeAxis.setLabelFont(rangeAxis.getLabelFont().deriveFont(30F)); 
		rangeAxis.setTickLabelFont(rangeAxis.getTickLabelFont().deriveFont(25F)); 
		
		chart.getTitle().setFont(JFreeChart.DEFAULT_TITLE_FONT.deriveFont(60F));
	}

}
