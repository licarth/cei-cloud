package VSCIFP;

import common.benchmark.OptimalExecutionStats;
import VSCIFP.algs.CIFFD;
import VSCIFP.algs.CNFL;
import VSCIFP.algs.NFLWithBinTypeOptimization;
import VSCIFP.benchmark.OptimalVSCIFPExecution;
import VSCIFP.gens.LinearVSCIFPGenerator;
import VSCIFP.gens.MonotoneVSCIFPGenerator;

public class MonotoneGenerationBenchmark {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		VSCIFP p = new VSCIFP(100, 20, 1, 1);
		MonotoneVSCIFPGenerator monoGen = new MonotoneVSCIFPGenerator(p, 1000);
		
		int nRuns = 1000;
		
		CIFFD ciffd = new CIFFD();
		CNFL nfl = new CNFL();
		NFLWithBinTypeOptimization nflOpt = new NFLWithBinTypeOptimization();
		
		OptimalVSCIFPExecution benchmarkCIFFD = new OptimalVSCIFPExecution(p, ciffd, monoGen, nRuns);
		OptimalExecutionStats<VSCIFP, VSCIFPInstance> stats = benchmarkCIFFD.run();
		OptimalVSCIFPExecution benchmarkNFL = new OptimalVSCIFPExecution(p, nfl, monoGen, nRuns);
		OptimalExecutionStats<VSCIFP, VSCIFPInstance> stats2 = benchmarkNFL.run();
		OptimalVSCIFPExecution benchmarkNFLOPT = new OptimalVSCIFPExecution(p, nflOpt, monoGen, nRuns);
		OptimalExecutionStats<VSCIFP, VSCIFPInstance> stats3 = benchmarkNFLOPT.run();
		
		System.out.println(stats);
		System.out.println(stats2);
		System.out.println(stats3);
	}

}
