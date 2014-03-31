package VSCIFP.benchmark;

import VSCIFP.VSCIFP;
import VSCIFP.VSCIFPInstance;
import VSCIFP.gens.VSCIFPGenerator;
import common.algorithm.Algorithm;
import common.benchmark.OptimalExecutionStats;
import common.benchmark.OptimalCostExecution;
import common.benchmark.RelativeExecutionStats;
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
public class RelativeVSCIFPExecution extends RelativeCostBenchmark<VSCIFP, VSCIFPInstance, Algorithm<VSCIFP, VSCIFPInstance>, VSCIFPGenerator> {

	public RelativeVSCIFPExecution(VSCIFP problem,
			Algorithm<VSCIFP, VSCIFPInstance> algorithm,
			VSCIFPGenerator generator, int runCount) {
		super(problem, algorithm, generator, runCount);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public RelativeExecutionStats<VSCIFP, VSCIFPInstance> run() throws Exception {
		
		//Create instances
		
		RelativeExecutionStats<VSCIFP, VSCIFPInstance> bs = new RelativeExecutionStats<>(this);
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
