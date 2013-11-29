package binpacking.gens;

import binpacking.BPP;

import common.AbstractRandomGenerator;

abstract class BPPGenerator extends AbstractRandomGenerator<BPP>{
	
	/**
	 * Number of items to generate.
	 */
	protected int numberOfItems;
	
	
	public BPPGenerator(BPP problem, int numberOfItems) {
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
