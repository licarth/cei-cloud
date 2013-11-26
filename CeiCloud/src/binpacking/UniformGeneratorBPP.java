package binpacking;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.management.RuntimeErrorException;

import common.Generator;
import common.ProblemInputDataException;
import common.StatUtils;
import common.Utils;
import common.VizUtils;

public class UniformGeneratorBPP implements Generator<BPP> {

	private int numItems = 2000;
	private int itemMaxSize = 10;
	private int binsize = 10;

	private int optimalSolution = 0;
	private int itemsPutIntoBins = 0;

	private ArrayList<Bin> bins = new ArrayList<Bin>();

	Random r = new Random(SEED);

	@Override
	public BPP generateInstance() {

		BPP p = null;

		while(itemsPutIntoBins < numItems){
			bins.add(generateOneBin());
		}
		
		try {
			p = new BPP(binsize, getAllItemsRandom());
		} catch (ProblemInputDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Test du chi II
		System.out.println("Chi II Test: "+((StatUtils.isRandom(p.getItemSizes(), itemMaxSize)) ? "PASSED" : "FAILED"));
		VizUtils.barChart(p.getItemSizes(), binsize+1);
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

	@Override
	public long getSeed() {
		return SEED;
	}

	@Override
	public List<BPP> generateInstances(int n) {
		List<BPP> l = new ArrayList<BPP>();
		for (int i = 0; i < n; i++) {
			l.add(generateInstance());
		}
		return l;
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
