package binpacking.gens;

import binpacking.BPP;

import common.AbstractRandomGenerator;

abstract class BPPGenerator extends AbstractRandomGenerator<BPP>{

	protected int numItems = 2000;
	protected int itemMaxSize = 10;
	protected int binsize = 10;
	
}
