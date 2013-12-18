package VSCIFP;

import static common.Utils.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
	public Set<BinType> binTypes = new TreeSet<>();

	//Generates bin types
	int optimalCost = 0;
	/**
	 * Closed bins.
	 */
	private List<Bin> bins = new ArrayList<>();
	private List<Bin> openBins = new ArrayList<>();
	private List<Integer> itemsPut = new ArrayList<Integer>();

	public VSCIFPInstance(VSCIFP problem) throws InputDataException {
		super(problem);
		checkProblemInput();
	}

	@Override
	public void checkProblemInput() throws InputDataException {
		//TODO Check inputs.
		//Checks that a bigger bin is never less efficient than a smaller bin.
		List<BinType> sortedBinTypes = asSortedList(binTypes, false);
		System.out.println(sortedBinTypes);
		double eff = 0;
		for (BinType binType : sortedBinTypes) {
			if (eff > binType.efficiency()){
				throw new InputDataException("Bin types (A,B) were found such as cap(A) > cap (B) and effiency(A) < efficiency(B)");
			}
			else eff = binType.efficiency();
		}		
	}

	@Override
	public String toStringDetailed() {
		return getBins().toString();
	}

	@Override
	public int getOptimalCost() {
		return optimalCost;
	}

	public List<Bin> getBins() {
		return bins;
	}

	public void setBins(List<Bin> bins) {
		this.bins = bins;
	}

	public void addItemToBin(Bin bin, int item) {
		try {
			bin.add(item);
			itemsPut.add(item);
			optimalCost += item;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setOptimalCost(int optimalCost) {
		this.optimalCost = optimalCost;
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

	public List<Integer> getItemsPut() {
		return itemsPut;
	}

	public void setItemsPut(List<Integer> itemsPut) {
		this.itemsPut = itemsPut;
	}

	public Set<BinType> getBinTypes() {
		return binTypes;
	}

	public void setBinTypes(Set<BinType> binTypes) {
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
