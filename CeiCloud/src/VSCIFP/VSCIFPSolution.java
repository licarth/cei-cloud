package VSCIFP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import common.VizUtils;
import common.algorithm.IAlgorithm;
import common.problem.IOptimalCostAwareSolution;
import common.solution.AbstractSolution;

/**
 * This class describes a solution to the VSCIFP problem.
 * It has methods that help building the solution. (add bins, close bins, etc.)
 * 
 * @author thomas
 *
 */
public class VSCIFPSolution extends AbstractSolution<VSCIFP, VSCIFPInstance> implements IOptimalCostAwareSolution {

	int totalCost = 0;
	/**
	 * Closed bins.
	 */
	private List<Bin> bins = new ArrayList<>();
	private List<Bin> openBins = new ArrayList<>();
	
	/**
	 * Item sizes.
	 */
	private List<Integer> itemSizes = new ArrayList<Integer>();

	public List<Bin> getBins() {
		return bins;
	}

	public void setBins(List<Bin> bins) {
		this.bins = bins;
	}

	public void addItemToBin(Bin bin, int item) {
		try {
			if (bin.isEmpty() & item != 0) {
				//When we need to put the first item in the bin
				//Then we have used this bin, its total cost gets counted
				//in total cost.
				totalCost += bin.getType().getCost();
			}
			bin.add(item);
			itemSizes.add(item);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}
	
	public List<Integer> getItemSizes() {
		return itemSizes;
	}

	public void setItemSizes(List<Integer> itemSizes) {
		this.itemSizes = itemSizes;
	}

	public void addClosedBin(Bin bin) {
		bins.add(bin);
	}
	
	public VSCIFPSolution(IAlgorithm<VSCIFP, VSCIFPInstance> sourceAlgorithm,
			VSCIFPInstance instance) {
		super(sourceAlgorithm, instance);
	}

	@Override
	public int getCost() {
		return totalCost;
	}

	public List<Bin> getOpenBins() {
		return openBins;
	}

	public void setOpenBins(List<Bin> openBins) {
		this.openBins = openBins;
	}
}
