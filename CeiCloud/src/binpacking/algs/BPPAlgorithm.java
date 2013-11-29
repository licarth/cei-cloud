package binpacking.algs;

import binpacking.BPPInstance;

import common.OfflineAlgorithm;

public abstract class BPPAlgorithm extends OfflineAlgorithm<BPPInstance> {

	BPPInstance instance;
	
	@Override
	public BPPInstance getInstance() {
		return instance;
	}
}
