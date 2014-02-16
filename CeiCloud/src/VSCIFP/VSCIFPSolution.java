package VSCIFP;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import VSCIFP.algs.Item;
import VSCIFP.algs.SolutionItem;

import common.Utils;
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

	@Override
	public VSCIFPSolution clone() throws CloneNotSupportedException {
		
		VSCIFPSolution clone = new VSCIFPSolution(this.sourceAlgorithm, getInstance());
		
		clone.setTotalCost(totalCost);
		clone.setBins(Utils.cloneBinHashSet(bins));
		
		return clone;
	}

	int totalCost = 0;
	/**
	 * Closed bins. It can be a big set.
	 */
	private HashSet<Bin> bins = new HashSet<>();
	/**
	 * Small list usually.
	 */
	private List<Bin> openBins = new ArrayList<>();
	//	/**
	//	 * List of items that already have been split, and resulting parts in the list.
	//	 */
	//	private Map<Item, HashSet<Item>> itemsCut = new HashMap<Item, HashSet<Item>>();

	/**
	 * Item sizes.
	 */
	private List<SolutionItem> items = new ArrayList<SolutionItem>();
	
//	/**
//	 * Items are sorted in a binary tree.
//	 */
//	private TreeSet<SolutionItem> itemsBinaryTree = new TreeSet<>(Collections.reverseOrder());

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
	public void addItemToBinForOptimalSolutionBuilding(Bin bin, SolutionItem item) {
		try {
			if (bin.isEmpty() & item.getSize() != 0) {
				//When we need to put the first item in the bin
				//Then we have used this bin, its total cost gets counted
				//in total cost.
				totalCost += bin.getType().getCost();
			}
			items.add(item);
			bin.add(item);
//			getInstance().getItems().add(item);		//Adds it to the instance list of items that have been put in bins.
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

	//	public int getTimesCut(Item item){
	//		Item root = item.getRoot();
	//		if (itemsCut.containsKey(root)){
	//			return itemsCut.get(root).size() - 1;
	//		} else return 0;
	//	}

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
		setItemsFromInstance(instance);
	}

	private void setItemsFromInstance(VSCIFPInstance instance) {
		for (Item i : instance.getItems()) {
			items.add(new SolutionItem(i, this));
		}
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

	public List<SolutionItem> getItems() {
		return items;
	}
	
	/**
	 * Retourne tous les items en parcourant tous les arbres.
	 * 
	 * @return
	 */
	public List<SolutionItem> getItemLeaves() {
		//Traverses all the trees whose roots are items. Return all leaves.
		List<SolutionItem> itemLeaves = new ArrayList<SolutionItem>();
		for (SolutionItem item : items) {
			itemLeaves.addAll(item.getLeaves());
		}
		return itemLeaves;
	}

	public void setItems(List<SolutionItem> items) {
		this.items = items;
	}
}
