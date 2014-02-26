package VSCIFP.benchmark;

import VSCIFP.VSCIFP;
import VSCIFP.VSCIFPInstance;
import VSCIFP.gens.VSCIFPGenerator;
import common.algorithm.Algorithm;
import common.benchmark.OptimalBenchmarkStats;
import common.benchmark.OptimalCostBenchmark;
import common.benchmark.RelativeBenchmarkStats;
import common.benchmark.RelativeCostBenchmark;
import common.problem.InputDataException;
import common.solution.ISolution;
import common.solution.OptimalCostNotKnownException;

/**
 * A benchmark for VSCIFP.
 * 
 * @author thomas
 *
 */
public class RelativeVSCIFPBenchmark extends RelativeCostBenchmark<VSCIFP, VSCIFPInstance, Algorithm<VSCIFP, VSCIFPInstance>, VSCIFPGenerator> {

	public RelativeVSCIFPBenchmark(VSCIFP problem,
			Algorithm<VSCIFP, VSCIFPInstance> algorithm,
			VSCIFPGenerator generator, int runCount) {
		super(problem, algorithm, generator, runCount);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public RelativeBenchmarkStats<VSCIFP, VSCIFPInstance> run() throws Exception {
		
		//Create instances
		
		RelativeBenchmarkStats<VSCIFP, VSCIFPInstance> bs = new RelativeBenchmarkStats<>(this);
		for (int j = 0; j < getRunCount(); j++) {
			try {
				VSCIFPInstance i = getGenerator().generateInstance();
				ISolution<VSCIFP, VSCIFPInstance> sol = getAlgorithm().solve(i);
				
				//For the stats
					bs.addCost(sol.getCost());
//				VizUtils.barChart(i.getItemSizes(), getProblem().getItemMinSize(), getProblem().getItemMaxSize());
			} catch (InputDataException e) {
				e.printStackTrace();
			}
		}
		return bs;
	
	}


}
