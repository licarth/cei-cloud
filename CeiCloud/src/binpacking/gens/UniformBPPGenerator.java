package binpacking.gens;

import java.util.Random;

import binpacking.BPP;

import common.ProblemInputDataException;

public class UniformBPPGenerator extends BPPGenerator {

	Random r = getRandom();
	
	@Override
	public BPP generateInstance() {
		BPP p = null;
		int[] itemSizes = new int[numItems];
		for (int i = 0; i < itemSizes.length; i++) {
			itemSizes[i] = 	r.nextInt(itemMaxSize) + 1;
		}
		
		try {
			p = new BPP(binsize, itemSizes, itemMaxSize);
		} catch (ProblemInputDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(p);
		return p;
	}

	@Override
	public long getSeed() {
		return SEED;
	}

}
