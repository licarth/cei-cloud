package binpacking;

import java.math.BigInteger;
import java.util.List;

import common.StatUtils;
import common.Utils;
import common.problem.Instance;
import common.problem.InputDataException;

public class BPPInstance extends Instance<BPP> {

	/**
	 *	Items sizes. 
	 */
	private List<Integer> itemSizes;		//L
	/**
	 * Optional, name of the instance.
	 */
	private String name;

	public BPPInstance(BPP problem, List<Integer> itemSizes) throws InputDataException{
		super(problem);
		this.itemSizes = itemSizes;
		checkProblemInput();
	}

//		@Override
//		public String toStringDetailed() {
//			StringBuilder sb = new StringBuilder();
//			sb.append(String.format("binsize=%s,costLimit=%s,itemSizes=%s", getBinSize(), getCostLimit(), Arrays.toString(itemSizes)));
//			return sb.toString();
//		}

	@Override
	public void checkProblemInput() throws InputDataException{
		//Check if we stays below MAXINT.
		if (Utils.bigSum(itemSizes).compareTo(new BigInteger(Integer.MAX_VALUE+"")) > 0) 
			throw new InputDataException(String.format(
					"Sum must be smaller than biggest integer : here sum is %s and biggest int is %s",
					Utils.bigSum(itemSizes),
					Integer.MAX_VALUE));

		if (problem.hasCostLimit()){
			if (new BigInteger(problem.getCostLimit()+"").multiply(new BigInteger(problem.getBinSize()+"")).compareTo(new BigInteger(Integer.MAX_VALUE+"")) <= 0) {
				if (Utils.sum(itemSizes) > problem.getCostLimit() * problem.getBinSize()) throw new InputDataException("There should be enough space for items to fit!");
			}
		}

		for (int i : itemSizes) {
			if (i > problem.getBinSize()){
				throw new InputDataException("Items should be smaller than bins!");
			}
			if (i <= 0) throw new InputDataException("Items sizes should be stricly positive integers!");
		}
	}

	public boolean checkUniformItemsRepartition() {
		boolean passed = false;
		String string = "Chi II Test: [";
		try {
			passed = StatUtils.isRandom(this.getItemSizes(), problem.getItemMaxSize());
			string += ((passed) ? "PASSED" : "FAILED");
		} catch (Exception e) {
			string += "FAILED : "+e.getMessage();
		}
		string += "]";
		System.out.println(string);
		return passed;
	}

	public int getNumberOfItems() {
		return itemSizes.size();
	}


	public List<Integer> getItemSizes() {
		return itemSizes;
	}

	public void setItemSizes(List<Integer> itemSizes) {
		this.itemSizes = itemSizes;
	}

	@Override
	public String toStringDetailed() {
		StringBuilder sb = new StringBuilder();
		if (getName() != null) sb.append(getName());
		sb.append(String.format("numOfItems=%s, itemSizes=%s", getItemSizes().size(), getItemSizes()));
		return sb.toString();
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
