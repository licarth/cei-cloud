package VSCIFP;

import static common.Utils.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import VSCIFP.algs.Item;

import com.google.common.collect.Iterators;
import common.VizUtils;
import common.problem.IOptimalCostAwareInstance;
import common.problem.InputDataException;
import common.problem.Instance;

/**
 * @author thomas
 *
 */
public class VSIFPInstance extends Instance<VSIFP> implements IOptimalCostAwareInstance {

	public VSIFPInstance(VSIFP problem) {
		super(problem);
		// TODO Auto-generated constructor stub
	}

	/**
	 * This solution is used for creating the instance.
	 */
	private VSIFPSolution optimalSolution;

	/**
	 *	Bin Types (all different)
	 */
	public TreeSet<BinType> binTypes = new TreeSet<>();
	
	/**
	 * Items to be used as an input to algorithms.
	 */
	public List<Item> items = new ArrayList<>();

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

	public TreeSet<BinType> getBinTypes() {
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
		List<Integer> l = new ArrayList<Integer>();
		for (Item i : getItems()) {
			l.add(i.getSize());
		}
		return l;
	}
	
	public List<Item> getItems() {
		return items;
	}

	/**
	 * Returns the bin type of given capacity (unique, binTypes is a set!).
	 * 
	 * @param i
	 * @return
	 * @throws Exception 
	 */
	public BinType getBinTypeOfCapacity(int capacity) throws Exception {
		if (!(capacity>0 && capacity<=getProblem().getMaxBinCapacity()))
			throw new ArrayIndexOutOfBoundsException("Capacity must be over 0 and below problem's max capacity.");
		Iterator<BinType> it = getBinTypes().iterator();
		BinType res = null;
		for (BinType bt : getBinTypes()) {
			if (bt.getCapacity() == capacity) return bt;
		}
		throw new Exception(String.format("Bin type of capacity %s does not exist", capacity));
	}

	/**
	 * Returns binType number i in size decreasing order.
	 * 
	 * @param i
	 * @return
	 */
	public BinType getBinTypeByIndex(int i) {
		return Iterators.get(getBinTypes().descendingIterator(), i);
	}

	public BinType getBinTypeOfMaxCapacity() {
		return getBinTypeByIndex(0);
	}

}
