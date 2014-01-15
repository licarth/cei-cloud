package VSCIFP;

import java.util.ArrayList;
import java.util.List;

import VSCIFP.algs.Item;

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

	//	/**
	//	 * Item sizes.
	//	 */
	//	private List<Item> items = new ArrayList<Item>();

	public List<Bin> getBins() {
		return bins;
	}

	public void setBins(List<Bin> bins) {
		this.bins = bins;
	}

	/**
	 * Only use in generator classes when building the optimal solution !
	 * This method changes getInstance().getItems() !
	 * 
	 * @param bin
	 * @param item
	 */
	public void addItemToBinForOptimalSolutionBuilding(Bin bin, Item item) {
		try {
			if (bin.isEmpty() & item.getSize() != 0) {
				//When we need to put the first item in the bin
				//Then we have used this bin, its total cost gets counted
				//in total cost.
				totalCost += bin.getType().getCost();
			}
			bin.add(item);
			getInstance().getItems().add(item);		//Adds it to the instance list of items that have been put in bins.
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param bin
	 * @param item
	 */
	public void addItemToBin(Bin bin, Item item) {
		try {
			if (bin.isEmpty() & item.getSize() != 0) {
				//When we need to put the first item in the bin
				//Then we have used this bin, its total cost gets counted
				//in total cost.
				totalCost += bin.getType().getCost();
			}
			bin.add(item);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void unpack(Bin bin) {
		bins.
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	//	public List<Item> getItems() {
	//		return items;
	//	}
	//
	//	public void setItems(List<Item> items) {
	//		this.items = items;
	//	}

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
