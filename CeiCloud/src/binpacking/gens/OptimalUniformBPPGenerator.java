package binpacking.gens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.management.RuntimeErrorException;

import binpacking.BPP;
import common.RandomGenerator;
import common.ProblemInputDataException;
import common.StatUtils;
import common.Utils;
import common.VizUtils;

public class OptimalUniformBPPGenerator extends BPPGenerator {

	private int optimalSolution = 0;
	private int itemsPutIntoBins = 0;

	private ArrayList<Bin> bins = new ArrayList<Bin>();

	Random r = getRandom();

	@Override
	public BPP generateInstance() {

		BPP p = null;

		while(itemsPutIntoBins < numItems){
			bins.add(generateOneBin());
		}
		
		try {
			p = new BPP(binsize, getAllItemsRandom(), itemMaxSize);
		} catch (ProblemInputDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Test du chi II
		System.out.println("Optimal solution: "+optimalSolution);
		System.out.println(p);
		return p;
	}

	/**
	 * Put all items of list bins into an array of size <code>numItems</code>.
	 * 
	 * @return
	 */
	private int[] getAllItemsRandom() {
		int[] items = new int[numItems];
		int n=0;
		for (Bin bin : bins) {
			ArrayList<Integer> binItems = bin.items;
			for (Integer i : binItems) {
				items[n] = i;
				n++;
			}
		}
		return items;
	}

	private Bin generateOneBin() {
		Bin bin = new Bin();

		while(itemsPutIntoBins < numItems && !bin.isFull()){
			if (itemsPutIntoBins > numItems) throw new RuntimeErrorException(null, "IMPOSSIBLE!");
			if (itemsPutIntoBins > numItems) throw new RuntimeErrorException(null, "IMPOSSIBLE!");
			int nextItem = nextItem();
			if (bin.sum() + nextItem > binsize) nextItem = binsize - bin.sum();
			bin.put(nextItem);
		}
		optimalSolution++;
		return bin;
	}

	private int nextItem() {
		return r.nextInt(itemMaxSize) + 1;
	}

	class Bin{
		private int capacity = binsize;
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

}
