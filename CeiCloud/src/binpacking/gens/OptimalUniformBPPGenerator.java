package binpacking.gens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.management.RuntimeErrorException;

import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.OptimalKnownBPPInstance;

import common.ProblemInputDataException;
import common.Utils;

public class OptimalUniformBPPGenerator extends BPPGenerator {
	
	public OptimalUniformBPPGenerator(BPP problem, int numberOfItems) {
		super(problem, numberOfItems);
	}

	private int optimalSolution = 0;
	private int itemsPutIntoBins = 0;

	private ArrayList<Bin> bins = new ArrayList<Bin>();

	Random r = getRandom();

	@Override
	public OptimalKnownBPPInstance generateInstance(BPP problem) {

		OptimalKnownBPPInstance inst = null;

		while(itemsPutIntoBins < numberOfItems){
			bins.add(generateOneBin());
		}
		
		try {
			inst = new OptimalKnownBPPInstance(problem, getAllItemsRandom(), optimalSolution);
		} catch (ProblemInputDataException e) {
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
			if (bin.sum() + nextItem > problem.getBinSize()) nextItem = problem.getBinSize() - bin.sum();
			bin.put(nextItem);
		}
		optimalSolution++;
		return bin;
	}

	private int nextItem() {
		return r.nextInt(problem.getItemMaxSize()) + 1;
	}

	class Bin{
		private int capacity = problem.getBinSize();
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
				l.add(generateInstance(problem));
			}
			return l;
		}

}
