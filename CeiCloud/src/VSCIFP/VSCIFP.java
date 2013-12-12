package VSCIFP;

import common.problem.AbstractProblem;

public class VSCIFP extends AbstractProblem{
	
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
	
	/**
	 *	Item max size (constraint of problem definition).
	 */
	protected int itemMaxSize = 99; //NONE
	
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
		return itemMaxSize;
	}

	public void setItemMaxSize(int itemMaxSize) {
		this.itemMaxSize = itemMaxSize;
	}

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
