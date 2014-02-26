package binpacking.benchmark;

import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.OptimalKnownBPPInstance;

import common.algorithm.OfflineAlgorithm;
import common.benchmark.OptimalBenchmarkStats;
import common.benchmark.OptimalCostBenchmark;
import common.generator.OptimalRandomGenerator;
import common.problem.InputDataException;
import common.solution.ISolution;
import common.solution.OptimalCostNotKnownException;

/**
 * A benchmark that compares solutions to 
 * 
 * @author thomas
 *
 */
public class BPPOptimalBenchmark extends OptimalCostBenchmark<BPP, OptimalKnownBPPInstance, OfflineAlgorithm<BPP, BPPInstance>, OptimalRandomGenerator<BPP, OptimalKnownBPPInstance>> {
	
	public BPPOptimalBenchmark(BPP problem,
			OfflineAlgorithm<BPP, BPPInstance> algorithm,
			OptimalRandomGenerator<BPP, OptimalKnownBPPInstance> generator,
			int runCount) {
		super(problem, algorithm, generator, runCount);
	}

	@Override
	public OptimalBenchmarkStats<BPP, OptimalKnownBPPInstance> run() {
		//Create instances
		
		OptimalBenchmarkStats<BPP, OptimalKnownBPPInstance> bs = new OptimalBenchmarkStats<>(this);
		for (int j = 0; j < getRunCount(); j++) {
			try {
				OptimalKnownBPPInstance i = getGenerator().generateInstance();
				ISolution<BPP, BPPInstance> sol = getAlgorithm().solve(i);
				
				//For the stats
				try {
					bs.addRatio(sol.getErrorRatio());
				} catch (OptimalCostNotKnownException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				VizUtils.barChart(i.getItemSizes(), getProblem().getItemMinSize(), getProblem().getItemMaxSize());
			} catch (InputDataException e) {
				e.printStackTrace();
			}
		}
		return bs;
	}
}
