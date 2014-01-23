package binpacking;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import VSCIFP.algs.Item;

import common.StatUtils;
import common.Utils;
import common.problem.InputDataException;
import common.problem.Instance;

public class BPPInstance extends Instance<BPP> {

	/**
	 *	Items sizes. 
	 */
	private List<? extends Item> items;		//L
	/**
	 * Optional, name of the instance.
	 */
	private String name;

	public BPPInstance(BPP problem, List<? extends Item> items) throws InputDataException{
		super(problem);
		setItems(items);
		checkProblemInput();
	}
	
	

//		@Override
//		public String toStringDetailed() {
//			StringBuilder sb = new StringBuilder();
//			sb.append(String.format("binsize=%s,costLimit=%s,items=%s", getBinSize(), getCostLimit(), Arrays.toString(items)));
//			return sb.toString();
//		}

	@Override
	public void checkProblemInput() throws InputDataException{
		//Check if we stays below MAXINT.
		if (Utils.bigSum(getItemSizes()).compareTo(new BigInteger(Integer.MAX_VALUE+"")) > 0) 
			throw new InputDataException(String.format(
					"Sum must be smaller than biggest integer : here sum is %s and biggest int is %s",
					Utils.bigSum(getItemSizes()),
					Integer.MAX_VALUE));

		if (problem.hasCostLimit()){
			if (new BigInteger(problem.getCostLimit()+"").multiply(new BigInteger(problem.getBinSize()+"")).compareTo(new BigInteger(Integer.MAX_VALUE+"")) <= 0) {
				if (Utils.sum(getItemSizes()) > problem.getCostLimit() * problem.getBinSize()) throw new InputDataException("There should be enough space for items to fit!");
			}
		}

		for (Item i : getItems()) {
			if (i.getSize() > problem.getBinSize()){
				throw new InputDataException("Items should be smaller than bins!");
			}
			if (i.getSize() <= 0) throw new InputDataException("Items sizes should be stricly positive integers!");
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
		return items.size();
	}

	@Override
	public String toStringDetailed() {
		StringBuilder sb = new StringBuilder();
		if (getName() != null) sb.append(getName());
		sb.append(String.format("numOfItems=%s, items=%s", getItemSizes().size(), getItemSizes()));
		return sb.toString();
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	private void setItemsFromSizes(List<Integer> itemSizes) {
		setItems(Utils.fromIntegersToItems(itemSizes));
	}

	public List<Integer> getItemSizes() {
		List<Integer> l = new ArrayList<Integer>();
		for (Item i : getItems()) {
			l.add(i.getSize());
		}
		return l;
	}

	public List<? extends Item> getItems() {
		return items;
	}

	public void setItems(List<? extends Item> items) {
		this.items = items;
	}
	
}
