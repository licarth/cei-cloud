package VSCIFP;

import static common.Utils.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import common.Utils;
import common.VizUtils;
import common.problem.IOptimalCostAwareInstance;
import common.problem.Instance;
import common.problem.InputDataException;

/**
 * @author thomas
 *
 */
public class VSCIFPInstance extends Instance<VSCIFP> implements IOptimalCostAwareInstance {

	/**
	 *	Bin Types (all different)
	 */
	public TreeSet<BinType> binTypes = new TreeSet<>();

	//Generates bin types
	int totalCost = 0;
	/**
	 * Closed bins.
	 */
	private List<Bin> bins = new ArrayList<>();
	private List<Bin> openBins = new ArrayList<>();
	private List<Integer> itemSizes = new ArrayList<Integer>();

	public VSCIFPInstance(VSCIFP problem) throws InputDataException {
		super(problem);
		checkProblemInput();
	}

	@Override
	public void checkProblemInput() throws InputDataException {
		//TODO Check inputs.
		//Checks that a bigger bin is never less efficient than a smaller bin.
		List<BinType> sortedBinTypes = asSortedList(binTypes, false);
//		System.out.println(sortedBinTypes);
		double eff = 0;
		for (BinType binType : sortedBinTypes) {
			if (eff > binType.unitCost()){
				throw new InputDataException("Bin types (A,B) were found such as cap(A) > cap (B) and effiency(A) < efficiency(B)");
			}
			else eff = binType.unitCost();
		}		
	}

	@Override
	public String toStringDetailed() {
		return "totalCost: "+totalCost+", bins: "+getBins().toString();
	}

	@Override
	public int getTotalCost() {
		return totalCost;
	}

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
	
	public void displayBinTypeRepartition(int instanceCount) {
		VizUtils.drawHistogramBinCapacities(binTypes, getBinsCapacities(), "", "-"+instanceCount);
	}
	
//	public int[] getBinsCapacities() {
//		int[] capacities = new int[bins.size()];
//		for (int i = 0; i < capacities.length; i++) {
//			capacities[i] = bins.get(i).getType().capacity;
//		}
//		return capacities;
//	}
	public List<Integer> getBinsCapacities() {
		List<Integer> capacities = new ArrayList<>();
		for (Bin bin : bins) {
			capacities.add(bin.getType().capacity);
		}
		return capacities;
	}

	public List<Integer> getItemSizes() {
		return itemSizes;
	}

	public void setItemSizes(List<Integer> itemSizes) {
		this.itemSizes = itemSizes;
	}

	public Set<BinType> getBinTypes() {
		return binTypes;
	}

	public void setBinTypes(TreeSet<BinType> binTypes) {
		this.binTypes = binTypes;
	}

	public final List<Bin> getOpenBins() {
		return Collections.unmodifiableList(openBins);
	}

	public void setOpenBins(List<Bin> openBins) {
		this.openBins = openBins;
	}

	public void addClosedBin(Bin bin) {
		bins.add(bin);
	}


}
