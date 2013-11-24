package binpacking;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import common.Generator;
import common.ProblemInputDataException;

public class UniformGeneratorBPP implements Generator<BPP> {

	private int numItems = 2000;
	private int itemMaxSize = 200;
	private int binsize = 200;
	
	Random r = new Random(SEED);
	
	@Override
	public BPP generateInstance() {
		BPP p = null;
		int[] itemSizes = new int[numItems];
		for (int i = 0; i < itemSizes.length; i++) {
			itemSizes[i] = 	r.nextInt(itemMaxSize) + 1;
		}

		try {
			p = new BPP(binsize, itemSizes);
		} catch (ProblemInputDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(p);
		return p;
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

}
