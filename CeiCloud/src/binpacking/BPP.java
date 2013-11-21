package binpacking;
import java.math.BigInteger;

import common.Problem;
import common.ProblemInputDataException;
import common.Utils;


public class BPP implements Problem{

	/**
	 *	Bin size. 
	 */
	private int binSize;

	/**
	 * Cost limit of the packing. (i.e. Max number of bins used in BPP case)
	 * If no cost limit provided, then the worst case taken to set this limit will be : 1 bin per item.
	 */
	private int costLimit;

	/**
	 *	Items sizes. 
	 */
	private int[] itemSizes;		//L

	public BPP(int binSize, int[] itemSizes, int costLimit) throws ProblemInputDataException{
		super();
		this.binSize = binSize;
		this.costLimit = costLimit;
		this.itemSizes = itemSizes;
		checkProblemInput();
	}

	public BPP(int binSize, int[] itemSizes) throws ProblemInputDataException{
		super();
		this.binSize = binSize;
		this.costLimit = itemSizes.length;
		this.itemSizes = itemSizes;
		checkProblemInput();
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




}
