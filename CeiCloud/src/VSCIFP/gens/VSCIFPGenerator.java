package VSCIFP.gens;

import VSCIFP.VSCIFP;
import VSCIFP.VSCIFPInstance;

import common.generator.OptimalGenerator;

public abstract class VSCIFPGenerator extends OptimalGenerator<VSCIFP, VSCIFPInstance> {
	
	public VSCIFPGenerator(VSCIFP problem) {
		super(problem);
	}

	private int maxPackingCost = 10000;
	
//	public abstract getBinCost(cap)
	
//	@Override
//	public VSCIFPInstance generateInstance() {
//
//		//Generates bin types
//		Set<BinType> binTypes = new HashSet<>();
//		//For the largest bin, capacity = cost = maxBinCapacity
//		binTypes.add(new BinType(maxBinCapacity, maxBinCapacity));
//		//For other bins, 
//		for (int i = 0; i < numBinTypes-1; i++) {
//			int n = r.nextInt(maxBinCapacity - 1)+1; // uniform number in [1, maxBinCapacity - 1].
//			binTypes.add(new BinType(n, n)); //Linear cost
//		}
//		return new VSCIFPInstance(getProblem());
//	}

	@Override
	public long getSeed() {
		return SEED;
	}

	public int getMaxPackingCost() {
		return maxPackingCost;
	}

	public void setMaxPackingCost(int maxPackingCost) {
		this.maxPackingCost = maxPackingCost;
	}
	
}
