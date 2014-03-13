package VSCIFP;

import common.problem.AbstractProblem;
import common.problem.IProblem;

public class VSIFP extends AbstractProblem{
	
	public VSIFP(int binCapacity, int typesOfBinCount, int itemMinSize,
			int maxNumSplits) {
		super();
		this.binCapacity = binCapacity;
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
	public int binCapacity = 100;	//D
	
	public int getMaxNumSplits() {
		return maxNumSplits;
	}

	public void setMaxNumSplits(int maxNumSplits) {
		this.maxNumSplits = maxNumSplits;
	}

	public int getMaxBinCapacity() {
		return binCapacity;
	}

	public void setMaxBinCapacity(int maxBinCapacity) {
		this.binCapacity = maxBinCapacity;
	}

	public int getItemMaxSize() {
		return binCapacity * (maxNumSplits +1);
	}

	@Override
	public boolean isSameProblemAs(IProblem other) {
		// TODO Auto-generated method stub
		return false;
	}

}
