package binpacking.benchmark;

import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.OptimalKnownBPPInstance;
import common.algorithm.OfflineAlgorithm;
import common.benchmark.OptimalExecutionStats;
import common.benchmark.OptimalCostExecution;
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
public class BPPOptimalBenchmark extends OptimalCostExecution<BPP, OptimalKnownBPPInstance, OfflineAlgorithm<BPP, BPPInstance>, OptimalRandomGenerator<BPP, OptimalKnownBPPInstance>> {
	
	public BPPOptimalBenchmark(BPP problem,
			OfflineAlgorithm<BPP, BPPInstance> algorithm,
			OptimalRandomGenerator<BPP, OptimalKnownBPPInstance> generator,
			int runCount) {
		super(problem, algorithm, generator, runCount);
	}

	@Override
	public OptimalExecutionStats<BPP, OptimalKnownBPPInstance> run() {
		//Create instances
		
		OptimalExecutionStats<BPP, OptimalKnownBPPInstance> bs = new OptimalExecutionStats<>(this);
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bs;
	}
}
