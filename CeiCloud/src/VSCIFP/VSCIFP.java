package VSCIFP;

import common.problem.AbstractProblem;

public class VSCIFP extends AbstractProblem{
	
	public VSCIFP(int maxBinCapacity, int typesOfBinCount, int itemMinSize,
//			int itemMaxSize,
			int maxNumSplits) {
		super();
		this.maxBinCapacity = maxBinCapacity;
		this.typesOfBinCount = typesOfBinCount;
		this.itemMinSize = itemMinSize;
//		this.itemMaxSize = itemMaxSize;
		this.maxNumSplits = maxNumSplits;
		checkFeasibility();
	}
	
	private void checkFeasibility() {
//		if (binCapacity * (maxNumSplits +1) < itemMaxSize) throw new RuntimeException("Problem is potentially not feasible.");
	}
	

	/**
	 * Number of splits allowed per item
	 */
	public int maxNumSplits = 0;	//D
	
	/**
	 * Number of splits allowed per item
	 */
	public int maxBinCapacity = 100;	//D
	/**
	 * Cost limit of the packing.
	 */
	public int costLimit;		//e
	/**
	 * Number of different types of bins.
	 * 
	 */
	public int typesOfBinCount = 10; //m
	
//	/**
//	 *	Item max size (constraint of problem definition).
//	 */
//	protected int itemMaxSize = binCapacity; //NONE
	
	/**
	 *	Item min size. (constraint of problem definition).
	 */
	protected int itemMinSize = 1; //NONE
	
	
	public int getMaxNumSplits() {
		return maxNumSplits;
	}

	public void setMaxNumSplits(int maxNumSplits) {
		this.maxNumSplits = maxNumSplits;
	}

	public int getCostLimit() {
		return costLimit;
	}

	public void setCostLimit(int costLimit) {
		this.costLimit = costLimit;
	}

	public int getTypesOfBinCount() {
		return typesOfBinCount;
	}

	public void setTypesOfBinCount(int typesOfBinCount) {
		this.typesOfBinCount = typesOfBinCount;
	}

	public int getMaxBinCapacity() {
		return maxBinCapacity;
	}

	public void setMaxBinCapacity(int maxBinCapacity) {
		this.maxBinCapacity = maxBinCapacity;
	}

	public int getItemMaxSize() {
		return maxBinCapacity * (maxNumSplits +1);
	}

//	public void setItemMaxSize(int itemMaxSize) {
//		this.itemMaxSize = itemMaxSize;
//	}

	public int getItemMinSize() {
		return itemMinSize;
	}

	public void setItemMinSize(int itemMinSize) {
		this.itemMinSize = itemMinSize;
	}

//	public int[] itemSizes;	//L	
//	public int[] binTypesCapacities;	//B
//	public int[] binCosts;	//C

}
