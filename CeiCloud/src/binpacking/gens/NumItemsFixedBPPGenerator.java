package binpacking.gens;

import binpacking.BPP;
import binpacking.BPPInstance;
import common.generator.AbstractRandomGenerator;

public abstract class NumItemsFixedBPPGenerator extends AbstractRandomGenerator<BPP, BPPInstance>{
	
	/**
	 * Number of items to generate.
	 */
	protected int numberOfItems;
	
	
	public NumItemsFixedBPPGenerator(BPP problem, int numberOfItems) {
		super(problem);
		this.numberOfItems = numberOfItems;
	}


	public int getNumberOfItems() {
		return numberOfItems;
	}


	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}
	
//	protected int numItems = 2000;
//	protected int itemMaxSize = 15;
//	protected int binsize = 100;
	
}
