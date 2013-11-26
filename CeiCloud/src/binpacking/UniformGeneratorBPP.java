package binpacking;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.management.RuntimeErrorException;

import common.Generator;
import common.ProblemInputDataException;
import common.Utils;

public class UniformGeneratorBPP implements Generator<BPP> {

	private int numItems = 20;
	private int itemMaxSize = 200;
	private int binsize = 200;

	private int binsGenerated = 0;
	private int itemsPutIntoBins = 0;

	private ArrayList<Bin> bins;

	Random r = new Random(SEED);

	@Override
	public BPP generateInstance() {

		BPP p = null;

		boolean permutationFound = false;

		//		while(!permutationFound){
		//			if (itemsPutIntoBins > numItems){
		//				ArrayList<Bin> permutation = searchPermutation(bins);
		//				if (permutation != null){
		//					permutationFound = true;
		//					bins = permutation;
		//					try {
		//						p = new BPP(binsize, getAllItemsRandom());
		//					} catch (ProblemInputDataException e) {
		//						// TODO Auto-generated catch block
		//						e.printStackTrace();
		//					}
		//				}
		//			}
		//		}
		
		while(itemsPutIntoBins < numItems){
			generateOneBin();
		}
		
		try {
			p = new BPP(binsize, getAllItemsRandom());
		} catch (ProblemInputDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

	private void generateOneBin() {
		Bin bin = new Bin();

		while(itemsPutIntoBins < numItems || !bin.isFull()){
			if (itemsPutIntoBins > numItems) throw new RuntimeErrorException(null, "IMPOSSIBLE!");
			int nextItem = nextItem();
			if (bin.sum() + nextItem > binsize) nextItem = binsize - bin.sum();
			bin.put(nextItem);
		}
		binsGenerated++;
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
