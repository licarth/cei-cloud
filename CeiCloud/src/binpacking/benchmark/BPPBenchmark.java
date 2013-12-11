package binpacking.benchmark;

import binpacking.BPP;
import binpacking.OptimalKnownBPPInstance;
import binpacking.algs.BPPAlgorithm;
import binpacking.tomasik.TomasikBPPGenerator;

import common.benchmark.OptimalCostBenchmark;

public class BPPBenchmark extends OptimalCostBenchmark<BPP, BPPAlgorithm, OptimalKnownBPPInstance, TomasikBPPGenerator> {

	public BPPBenchmark(BPP problem, BPPAlgorithm algorithm,
			TomasikBPPGenerator generator) {
		super(problem, algorithm, generator);
		// TODO Auto-generated constructor stub
	}

}
