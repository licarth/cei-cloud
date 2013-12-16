package binpacking.benchmark;

import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.OptimalKnownBPPInstance;
import binpacking.algs.BPPAlgorithm;

import common.benchmark.BenchmarkStats;
import common.benchmark.OptimalCostBenchmark;
import common.generator.OptimalRandomGenerator;
import common.problem.InputDataException;
import common.solution.Solution;

public class BPPOptimalBenchmark extends OptimalCostBenchmark<BPP, OptimalKnownBPPInstance, BPPAlgorithm, OptimalRandomGenerator<BPP, OptimalKnownBPPInstance>> {
	
	public BPPOptimalBenchmark(BPP problem, BPPAlgorithm algorithm,
			OptimalRandomGenerator<BPP, OptimalKnownBPPInstance> generator, int runCount) {
		super(problem, algorithm, generator, runCount);
	}

	@Override
	public BenchmarkStats<BPP, OptimalKnownBPPInstance> run() {
		//Create instances
		
		BenchmarkStats<BPP, OptimalKnownBPPInstance> bs = new BenchmarkStats<>(this);
		for (int j = 0; j < getRunCount(); j++) {
			try {
				OptimalKnownBPPInstance i = getGenerator().generateInstance();
				Solution<BPP, BPPInstance> sol = getAlgorithm().solve(i);
				
				//For the stats
				bs.addRatio((float)sol.getCost() / (float) i.getOptimalCost());
//				VizUtils.barChart(i.getItemSizes(), getProblem().getItemMinSize(), getProblem().getItemMaxSize());
			} catch (InputDataException e) {
				e.printStackTrace();
			}
		}
		return bs;
	}

}
