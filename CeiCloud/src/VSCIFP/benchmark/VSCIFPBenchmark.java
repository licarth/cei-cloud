package VSCIFP.benchmark;

import VSCIFP.VSCIFP;
import VSCIFP.VSCIFPInstance;
import VSCIFP.gens.VSCIFPGenerator;

import common.algorithm.Algorithm;
import common.benchmark.BenchmarkStats;
import common.benchmark.OptimalCostBenchmark;
import common.problem.InputDataException;
import common.solution.ISolution;
import common.solution.OptimalCostNotKnownException;

/**
 * A benchmark for VSCIFP.
 * 
 * @author thomas
 *
 */
public class VSCIFPBenchmark extends OptimalCostBenchmark<VSCIFP, VSCIFPInstance, Algorithm<VSCIFP, VSCIFPInstance>, VSCIFPGenerator> {

	public VSCIFPBenchmark(VSCIFP problem,
			Algorithm<VSCIFP, VSCIFPInstance> algorithm,
			VSCIFPGenerator generator, int runCount) {
		super(problem, algorithm, generator, runCount);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public BenchmarkStats<VSCIFP, VSCIFPInstance> run() {
		
		//Create instances
		
		BenchmarkStats<VSCIFP, VSCIFPInstance> bs = new BenchmarkStats<>(this);
		for (int j = 0; j < getRunCount(); j++) {
			try {
				VSCIFPInstance i = getGenerator().generateInstance();
				ISolution<VSCIFP, VSCIFPInstance> sol = getAlgorithm().solve(i);
				
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
