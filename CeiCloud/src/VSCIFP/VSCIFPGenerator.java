package VSCIFP;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import common.Generator;

public abstract class VSCIFPGenerator implements Generator<VSCIFP> {
	
	private final long seed = 4378578463L;

	protected Random r = new Random(seed);
	
	private int maxBinCapacity = 100;
	private int numBinTypes = 10;
	
//	public abstract getBinCost(cap)
	
	@Override
	public VSCIFP generateInstance() {

		//Generates bin types
		Set<BinType> binTypes = new HashSet<>();
		//For the largest bin, capacity = cost = maxBinCapacity
		binTypes.add(new BinType(maxBinCapacity, maxBinCapacity));
		//For other bins, 
		for (int i = 0; i < numBinTypes-1; i++) {
			int n = r.nextInt(maxBinCapacity - 1)+1; // uniform number in [1, maxBinCapacity - 1].
			binTypes.add(new BinType(n, n)); //Linear cost
		}
		return new VSCIFP(binTypes, 10);
	}

	@Override
	public long getSeed() {
		return seed;
	}
	
}
