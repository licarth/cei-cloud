package binpacking;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import VSCIFP.algs.Item;
import common.StatUtils;
import common.Utils;
import common.problem.InputDataException;
import common.problem.Instance;

public class BPPItemsInstance extends BPPInstance {

	/**
	 *	Items sizes. 
	 */
	private List<Item> items = new ArrayList<>();		//L
	/**
	 * Optional, name of the instance.
	 */
	private String name;

	public BPPItemsInstance(BPP problem, List<Integer> itemSizes) throws InputDataException{
		super(problem, itemSizes);
		setItemsFromSizes(itemSizes);
		checkProblemInput();
	}
	
//	public BPPItemsInstance(BPP problem, List<Item> items) throws InputDataException{
//		super(problem);
//		setItems(items.as);
//		checkProblemInput();
//	}

//		@Override
//		public String toStringDetailed() {
//			StringBuilder sb = new StringBuilder();
//			sb.append(String.format("binsize=%s,costLimit=%s,items=%s", getBinSize(), getCostLimit(), Arrays.toString(items)));
//			return sb.toString();
//		}

	private void setItemsFromSizes(List<Integer> itemSizes) {
			List<Item> l = new ArrayList<Item>();
			for (Integer i : itemSizes) {
				l.add(new Item(i));
			}
			setItems(l);
	}

	@Override
	public void checkProblemInput() throws InputDataException{
//		
//		for (Item i : getItems()) {
//			if (i.getSize() > problem.getBinSize()){
//				throw new InputDataException("Items should be smaller than bins!");
//			}
//			if (i.getSize() <= 0) throw new InputDataException("Items sizes should be stricly positive integers!");
//		}
//		
//		//Check if we stays below MAXINT.
//		if (Utils.bigSum(getItemSizes()).compareTo(new BigInteger(Integer.MAX_VALUE+"")) > 0) 
//			throw new InputDataException(String.format(
//					"Sum must be smaller than biggest integer : here sum is %s and biggest int is %s",
//					Utils.bigSum(getItemSizes()),
//					Integer.MAX_VALUE));
//
//		if (problem.hasCostLimit()){
//			if (new BigInteger(problem.getCostLimit()+"").multiply(new BigInteger(problem.getBinSize()+"")).compareTo(new BigInteger(Integer.MAX_VALUE+"")) <= 0) {
//				if (Utils.sum(getItemSizes()) > problem.getCostLimit() * problem.getBinSize()) throw new InputDataException("There should be enough space for items to fit!");
//			}
//		}
	}

	public boolean checkUniformItemsRepartition() {
		boolean passed = false;
		String string = "Chi II Test: [";
		try {
			passed = StatUtils.isRandom(getItemSizes(), problem.getItemMaxSize());
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
	
	public List<Integer> getItemSizes() {
		List<Integer> l = new ArrayList<Integer>();
		for (Item i : getItems()) {
			l.add(i.getSize());
		}
		return l;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
