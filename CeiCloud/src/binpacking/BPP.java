package binpacking;

import common.problem.Problem;


public class BPP extends AbstractBPP {
	/**
	 *	Bin size. A BPP problem has one single bin type of constant size.
	 */
	protected final int binSize;
	
	/**
	 *	Item max size (constraint of problem definition).
	 */
	protected int itemMaxSize; //NONE
	
	/**
	 *	Item min size. (constraint of problem definition).
	 */
	protected int itemMinSize = 0; //NONE
	
	/**
	 * Cost limit of the packing. (i.e. Max number of bins used in BPP case)
	 * If no cost limit provided, then the worst case taken to set this limit will be : 1 bin per item.
	 */
	protected int costLimit = -1; //NONE

	public BPP(int binSize) {
		this.binSize = binSize;
		this.itemMaxSize = binSize;
	}
	public BPP(int binSize, int itemMinSize, int itemMaxSize) {
		this(binSize);
		this.itemMinSize = itemMinSize;
		this.itemMaxSize = itemMaxSize;
	}
	
	@Override
	public boolean isSameProblemAs(Problem other) {
		if (!(other instanceof BPP)) return false;
		BPP o = (BPP) other;
		return binSize == o.binSize
				&& itemMaxSize == o.binSize
				&& itemMinSize == o.itemMinSize
				&& costLimit == o.costLimit;
		}	
	

	public int getBinSize() {
		return binSize;
	}
	
	public int getItemMaxSize() {
		return itemMaxSize;
	}
	
	public int getItemMinSize() {
		return itemMinSize;
	}
	
	public int getCostLimit() {
		return costLimit;
	}
	
	public void setItemMaxSize(int itemMaxSize) {
		this.itemMaxSize = itemMaxSize;
	}
	public void setItemMinSize(int itemMinSize) {
		this.itemMinSize = itemMinSize;
	}
	public void setCostLimit(int costLimit) {
		this.costLimit = costLimit;
	}
	public boolean hasCostLimit() {
		return (costLimit>0) ;
	}
	
	
}
