package binpacking.benchmark;

import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.algs.BPPAlgorithm;
import binpacking.tomasik.TomasikBPPGenerator;

import common.benchmark.GreedyBenchmark;

public class BPPBenchmark extends GreedyBenchmark<BPP, BPPInstance, BPPAlgorithm, TomasikBPPGenerator>{

	public BPPBenchmark(BPP problem, BPPAlgorithm algorithm,
			TomasikBPPGenerator generator) {
		super(problem, algorithm, generator);
		// TODO Auto-generated constructor stub
	}

}
