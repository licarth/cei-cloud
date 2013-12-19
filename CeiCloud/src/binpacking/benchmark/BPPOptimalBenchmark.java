package binpacking.benchmark;

import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.OptimalKnownBPPInstance;
import common.algorithm.OfflineAlgorithm;
import common.benchmark.BenchmarkStats;
import common.benchmark.OptimalCostBenchmark;
import common.generator.OptimalRandomGenerator;
import common.problem.InputDataException;
import common.solution.OptimalCostNotKnownException;
import common.solution.Solution;

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
	public BenchmarkStats<BPP, OptimalKnownBPPInstance> run() {
		//Create instances
		
		BenchmarkStats<BPP, OptimalKnownBPPInstance> bs = new BenchmarkStats<>(this);
		for (int j = 0; j < getRunCount(); j++) {
			try {
				OptimalKnownBPPInstance i = getGenerator().generateInstance();
				Solution<BPP, BPPInstance> sol = getAlgorithm().solve(i);
				
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
