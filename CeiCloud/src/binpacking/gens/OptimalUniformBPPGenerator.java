package binpacking.gens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.management.RuntimeErrorException;

import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.OptimalKnownBPPInstance;

import common.Utils;
import common.problem.InputDataException;

public class OptimalUniformBPPGenerator extends NumItemsFixedBPPGenerator {
	
	public OptimalUniformBPPGenerator(BPP problem, int numberOfItems) {
		super(problem, numberOfItems);
	}

	private int optimalSolution = 0;
	private int itemsPutIntoBins = 0;

	private ArrayList<Bin> bins = new ArrayList<Bin>();
	
	Random r = reset();

	@Override
	public OptimalKnownBPPInstance generateInstance() {

		OptimalKnownBPPInstance inst = null;

		while(itemsPutIntoBins < numberOfItems){
			bins.add(generateOneBin());
		}
		
		try {
			inst = new OptimalKnownBPPInstance(getProblem(), Utils.fromIntegersToItems(getAllItemsRandom()), optimalSolution);
		} catch (InputDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return inst;
	}

	/**
	 * Put all items of list bins into an array of size <code>numItems</code>.
	 * 
	 * @return
	 */
	private List<Integer> getAllItemsRandom() {
		ArrayList<Integer> items = new ArrayList<>();
		for (Bin bin : bins) {
			ArrayList<Integer> binItems = bin.items;
			for (Integer i : binItems) {
				items.add(i);
			}
		}
		return items;
	}

	private Bin generateOneBin() {
		Bin bin = new Bin();

		while(itemsPutIntoBins < numberOfItems && !bin.isFull()){
			if (itemsPutIntoBins > numberOfItems) throw new RuntimeErrorException(null, "IMPOSSIBLE!");
			int nextItem = nextItem();
			if (bin.sum() + nextItem > getProblem().getBinSize()) nextItem = getProblem().getBinSize() - bin.sum();
			bin.put(nextItem);
		}
		optimalSolution++;
		return bin;
	}

	private int nextItem() {
		return r.nextInt(getProblem().getItemMaxSize()) + 1;
	}

	class Bin{
		private int capacity = getProblem().getBinSize();
		private ArrayList<Integer> items = new ArrayList<Integer>();
		public boolean isFull() {
			return sum() == this.capacity;
		}
		public boolean isOverflowed(){
			return sum() > this.capacity;
		}
		public int sum(){
			return Utils.sum(items);
		}
		public void put(int i) {
			if (i>0){
				items.add(i);
				itemsPutIntoBins++;
			}
		}
	}

	@Override
	public List<BPPInstance> generateInstances(int n) {
			List<BPPInstance> l = new ArrayList<BPPInstance>();
			for (int i = 0; i < n; i++) {
				l.add(generateInstance());
			}
			return l;
		}

}
