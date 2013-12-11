package binpacking.algs;

import binpacking.BPP;
import binpacking.BPPInstance;

import common.algorithm.OfflineAlgorithm;

public abstract class BPPAlgorithm extends OfflineAlgorithm<BPP, BPPInstance> {

	BPPInstance instance;
	
	@Override
	public BPPInstance getInstance() {
		return instance;
	}
}
