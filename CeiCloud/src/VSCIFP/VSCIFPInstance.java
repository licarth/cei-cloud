package VSCIFP;

import static common.Utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import VSCIFP.algs.Item;

import common.VizUtils;
import common.problem.IOptimalCostAwareInstance;
import common.problem.InputDataException;
import common.problem.Instance;

/**
 * @author thomas
 *
 */
public class VSCIFPInstance extends Instance<VSCIFP> implements IOptimalCostAwareInstance {

	/**
	 * This solution is used for creating the instance.
	 */
	private VSCIFPSolution optimalSolution;
	
	/**
	 *	Bin Types (all different)
	 */
	public TreeSet<BinType> binTypes = new TreeSet<>();

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

	public String toStringDetailed() {
		return "bins: "+getBinTypes().toString();
	}

	public void displayBinTypeRepartition(int instanceCount) {
		VizUtils.drawHistogramBinCapacities(binTypes, getBinTypesCapacities(), "", "-"+instanceCount);
	}
	
	public List<Integer> getBinTypesCapacities() {
		List<Integer> capacities = new ArrayList<>();
		for (BinType binType : binTypes) {
			capacities.add(binType.capacity);
		}
		return capacities;
	}
	
	public Set<BinType> getBinTypes() {
		return binTypes;
	}

	public void setBinTypes(TreeSet<BinType> binTypes) {
		this.binTypes = binTypes;
	}

	public VSCIFPSolution getOptimalSolution() {
		return optimalSolution;
	}

	public void setOptimalSolution(VSCIFPSolution optimalSolution) {
		this.optimalSolution = optimalSolution;
	}

	public List<Integer> getItemSizes() {
		return optimalSolution.getItemSizes();
	}
	
	public List<Item> getItems() {
		List<Item> l = new ArrayList<Item>();
		for (Integer i : getItemSizes()) {
			l.add(new Item(i));
		}
		return l;
	}
}
