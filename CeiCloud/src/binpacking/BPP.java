package binpacking;
import java.math.BigInteger;
import java.util.Arrays;

import common.AbstractProblem;
import common.Problem;
import common.ProblemInputDataException;
import common.StatUtils;
import common.Utils;


public class BPP extends AbstractProblem {

	/**
	 *	Bin size. 
	 */
	private int binSize;

	/**
	 *	Item max size. 
	 */
	private int itemMaxSize;
	
	/**
	 *	Item min size. 
	 */
	private int itemMinSize;
	
	/**
	 * Cost limit of the packing. (i.e. Max number of bins used in BPP case)
	 * If no cost limit provided, then the worst case taken to set this limit will be : 1 bin per item.
	 */
	private int costLimit;

	/**
	 *	Items sizes. 
	 */
	private int[] itemSizes;		//L

	public BPP(int binSize, int[] itemSizes, int costLimit, int itemMaxSize) throws ProblemInputDataException{
		super();
		this.binSize = binSize;
		this.costLimit = costLimit;
		this.itemSizes = itemSizes;
		this.itemMaxSize = itemMaxSize;
		checkProblemInput();
	}

	public BPP(int binSize, int[] itemSizes, int itemMaxSize) throws ProblemInputDataException{
		super();
		this.binSize = binSize;
		this.costLimit = itemSizes.length;
		this.itemSizes = itemSizes;
		this.itemMaxSize = itemMaxSize;
		checkProblemInput();
	}
	
	@Override
	public String toStringDetailed() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("binsize=%s,costLimit=%s,itemSizes=%s", binSize, costLimit, Arrays.toString(itemSizes)));
		return sb.toString();
	}

	@Override
	public void checkProblemInput() throws ProblemInputDataException{
		//Check if we stays below MAXINT.
		if (Utils.bigSum(itemSizes).compareTo(new BigInteger(Integer.MAX_VALUE+"")) > 0) 
			throw new ProblemInputDataException(String.format(
					"Sum must be smaller than biggest integer : here sum is %s and biggest int is %s",
					Utils.bigSum(itemSizes),
					Integer.MAX_VALUE));

		if (new BigInteger(costLimit+"").multiply(new BigInteger(binSize+"")).compareTo(new BigInteger(Integer.MAX_VALUE+"")) <= 0) {
			if (Utils.sum(itemSizes) > costLimit * binSize) throw new ProblemInputDataException("There should be enough space for items to fit!");
		}
		
		for (int i : itemSizes) {
			if (i > binSize) throw new ProblemInputDataException("Items should be smaller than bins!");
			if (i <= 0) throw new ProblemInputDataException("Items sizes should be stricly positive integers!");
		}
	}
	
	public boolean checkUniformItemsRepartition() {
		System.out.println("Chi II Test: "+((StatUtils.isRandom(this.getItemSizes(), this.getItemMaxSize())) ? "PASSED" : "FAILED"));
		return StatUtils.isRandom(this.getItemSizes(), this.getItemMaxSize());
	}

	public int getNumberOfItems() {
		return itemSizes.length;
	}

	public int getBinSize() {
		return binSize;
	}


	public int getE() {
		return costLimit;
	}


	public int[] getItemSizes() {
		return itemSizes;
	}

	public int getItemMaxSize() {
		return itemMaxSize;
	}

	public void setItemMaxSize(int itemMaxSize) {
		this.itemMaxSize = itemMaxSize;
	}

	public int getCostLimit() {
		return costLimit;
	}

	public void setCostLimit(int costLimit) {
		this.costLimit = costLimit;
	}

	public void setBinSize(int binSize) {
		this.binSize = binSize;
	}

	public void setItemSizes(int[] itemSizes) {
		this.itemSizes = itemSizes;
	}

	public int getItemMinSize() {
		return itemMinSize;
	}

	public void setItemMinSize(int itemMinSize) {
		this.itemMinSize = itemMinSize;
	}




}
