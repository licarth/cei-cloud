package VSCIFP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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
	 * Closed bins. It can be a big set.
	 */
	private HashSet<Bin> bins = new HashSet<>();
	/**
	 * Small list usually.
	 */
	private List<Bin> openBins = new ArrayList<>();
	/**
	 * List of items that already have been split, and resulting parts in the list.
	 */
	private Map<Bin, HashSet<Item>> itemsCut = new HashMap<Bin, HashSet<Item>>();

	//	/**
	//	 * Item sizes.
	//	 */
	//	private List<Item> items = new ArrayList<Item>();

	public Set<Bin> getBins() {
		return bins;
	}

	public void setBins(HashSet<Bin> bins) {
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

	/**
	 * Unpacks and remove given bin.
	 * 
	 * @param bin
	 * @throws Exception 
	 */
	public List<Item> unpack(Bin bin) throws Exception {
		if (bins.remove(bin)){
			totalCost -= bin.getType().getCost();
			//Make a local copy of contents before flushing it.
			List<Item> content = new ArrayList<>(bin.getContent());
			bin.flush();
			return content;
		} else throw new Exception("Bin not found in solution packing.");
	}

	public int getTimesCut(Item item){
		if (itemsCut.containsKey(item)){
			return itemsCut.get(item).size();
		} else return 0;
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

	public Set<Item> getChildren(Item i) {
		return itemsCut.get(i);
	}
}
