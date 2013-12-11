package binpacking.benchmark;

import binpacking.BPP;
import binpacking.OptimalKnownBPPInstance;
import binpacking.algs.BPPAlgorithm;
import binpacking.algs.FFD;
import binpacking.tomasik.TomasikBPPGenerator;
import common.benchmark.OptimalCostBenchmark;
import common.generator.OptimalGenerator;

public class BPPOptimalBenchmark extends OptimalCostBenchmark<BPP, OptimalKnownBPPInstance, BPPAlgorithm, OptimalGenerator<BPP, OptimalKnownBPPInstance>> {

	public BPPOptimalBenchmark(BPP problem, BPPAlgorithm algorithm,
			OptimalGenerator<BPP, OptimalKnownBPPInstance> generator) {
		super(problem, algorithm, generator);
	}

	@Override
	public int getSampleSize() {
		return 1000;
	}

}
